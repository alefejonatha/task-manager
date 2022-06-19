package desafio.taskmanager.task.service;

import desafio.taskmanager.project.entity.Project;
import desafio.taskmanager.project.service.ProjectService;
import desafio.taskmanager.task.dto.TaskPostDTO;
import desafio.taskmanager.task.dto.TaskPutDTO;
import desafio.taskmanager.task.entity.Task;
import desafio.taskmanager.task.entity.TasksForToday;
import desafio.taskmanager.task.enums.Status;
import desafio.taskmanager.task.exception.TaskNotFoundException;
import desafio.taskmanager.task.mapper.TaskMapper;
import desafio.taskmanager.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectService projectService;


    public List<Task> findALl() {
        return taskRepository.findAll();
    }

    public List<Task> findByTitleStartingWith(String title) {
        return taskRepository.findByTitleStartingWith(title);
    }

    public Task findById(Long id) {
        return verifyAndGet(id);
    }

    public List<Task> findAllByProject(Long projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }

    public List<Task> findAllAndOrderByStatus() {
        return taskRepository.findByOrderByStatusDesc();
    }

    public List<Task> listTasksByFilters(LocalDate initialDate, LocalDate finalDate, boolean strict) {
        return taskRepository.listTasksByFilters(initialDate, finalDate, strict);
    }

    public TasksForToday listTasksForToday(LocalTime availableTime) {

        List<Task> tasks = taskRepository.findAllByOrderByPriorityAsc();
        if (tasks.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "task list is empty");
        }

        List<Task> filteredTasks = tasks
                .stream()
                .filter(x -> x.getStatus() == Status.UNFINISHED)
                .filter(x -> x.getTime().isBefore(availableTime) || x.getTime().equals(availableTime))
                .collect(Collectors.toList());

        if (filteredTasks.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "task list is empty");
        }

        List<Task> taskList = new ArrayList<>(Arrays.asList(filteredTasks.get(0)));
        LocalTime contTimeTasks = filteredTasks.get(0).getTime();

        for (int i = 1; i < filteredTasks.size(); i++) {
            if (availableTime.isAfter(sumTime(contTimeTasks, filteredTasks.get(i).getTime())) || availableTime.equals(sumTime(contTimeTasks, filteredTasks.get(i).getTime()))) {
                contTimeTasks = sumTime(contTimeTasks, filteredTasks.get(i).getTime());
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
    public Task save(TaskPostDTO taskPostDTO) {
        Project foundProject = projectService.findById(taskPostDTO.getProjectId());

        if (taskPostDTO.getFinalDate().isBefore(taskPostDTO.getInitialDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The initialDate cannot be greater than the finalDate");
        }

        Task taskToSave = TaskMapper.INSTANCE.toTask(taskPostDTO);
        taskToSave.setProject(foundProject);
        return taskRepository.save(taskToSave);
    }


    public Task update(TaskPutDTO taskPutDTO) {
        Task savedTask = verifyAndGet(taskPutDTO.getId());
        Task updatedTask = TaskMapper.INSTANCE.toTask(taskPutDTO);
        updatedTask.setProject(savedTask.getProject());

        return taskRepository.save(updatedTask);
    }

    public void delete(Long id) {
        taskRepository.delete(verifyAndGet(id));
    }

    public Task verifyAndGet(Long id) {
        Task foundTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return foundTask;
    }

    private LocalTime sumTime(LocalTime x, LocalTime y) {
        return x.plusHours(y.getHour()).plusMinutes(y.getMinute());
    }

}
