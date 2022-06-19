package desafio.taskmanager.task.service;

import desafio.taskmanager.project.entity.Project;
import desafio.taskmanager.project.service.ProjectService;
import desafio.taskmanager.task.dto.TaskPostRequestBody;
import desafio.taskmanager.task.dto.TaskPutRequestBody;
import desafio.taskmanager.task.entity.Task;
import desafio.taskmanager.task.entity.TasksForToday;
import desafio.taskmanager.task.enums.Status;
import desafio.taskmanager.task.mapper.TaskMapper;
import desafio.taskmanager.task.repository.TaskRepository;
import desafio.taskmanager.util.SumLocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final SumLocalTime sumLocalTime;

    public List<Task> listAllNonPageable() {
        return taskRepository.findAll();
    }

    public List<Task> findByTitleStartingWith(String title){
        return taskRepository.findByTitleStartingWith(title);
    }

    public Task findTaskByIdOrElseThrowException(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task not found"));
    }

    public List<Task> listTasksByProject(Long id) {
        return taskRepository.getTasksByProject(id);
    }

    public List<Task> findAllAndOrderByStatus() {
        return taskRepository.findAllOrderByStatus();
    }

    public List<Task> listTasksByFilters(LocalDate initialDate, LocalDate finalDate, boolean strict) {
        return taskRepository.listTasksByFilters(initialDate, finalDate, strict);
    }

    public TasksForToday listTasksForToday(LocalTime availableTime) {

        List<Task> tasks = taskRepository.findAllOrderByPriority();
        if (tasks.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "task list is empty");
        }

        List<Task> taskList = new ArrayList<>();

        List<Task> filteredTasks = tasks //TODO FILTRAR E TENTAR LANÇAR UMA EXCEÇÃO
                .stream()
                .filter(x -> x.getStatus() == Status.UNFINISHED)
                .filter(x -> x.getTime().isBefore(availableTime) || x.getTime().equals(availableTime))
                .collect(Collectors.toList());

        if (filteredTasks.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "task list is empty");
        }

        LocalTime contTimeTasks = filteredTasks.get(0).getTime();
        taskList.add(filteredTasks.get(0));

        for (int i = 1; i < filteredTasks.size(); i++) {
            if (availableTime.isAfter(sumLocalTime.sum(contTimeTasks, filteredTasks.get(i).getTime())) || availableTime.equals(sumLocalTime.sum(contTimeTasks, filteredTasks.get(i).getTime()))) {
                contTimeTasks = sumLocalTime.sum(contTimeTasks, filteredTasks.get(i).getTime());
                taskList.add(filteredTasks.get(i));
            }
        }

        TasksForToday tasksForToday = new TasksForToday();
        tasksForToday.setAvailableTime(availableTime);
        tasksForToday.setTotalTime(contTimeTasks);
        tasksForToday.setListTask(taskList);
        return tasksForToday;
    }

    @Transactional
    public Task save(TaskPostRequestBody taskPostRequestBody) {
        Project foundProject = projectService.findByIdOrElseThrowException(taskPostRequestBody.getProjectId());

        if (taskPostRequestBody.getFinalDate().isBefore(taskPostRequestBody.getInitialDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The initialDate cannot be greater than the finalDate");
        }

        Task taskToSave = TaskMapper.INSTANCE.toTask(taskPostRequestBody);
        taskToSave.setProject(foundProject);
        return taskRepository.save(taskToSave);
    }


    public void update(TaskPutRequestBody taskPutRequestBody) {
        Task savedTask = findTaskByIdOrElseThrowException(taskPutRequestBody.getId());
        Task updatedTask = TaskMapper.INSTANCE.toTask(taskPutRequestBody);
        updatedTask.setProject(savedTask.getProject());

        taskRepository.save(updatedTask);
    }

    public void delete(Long id) {
        taskRepository.delete(findTaskByIdOrElseThrowException(id));
    }
}
