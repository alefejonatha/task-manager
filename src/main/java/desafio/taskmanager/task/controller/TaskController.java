package desafio.taskmanager.task.controller;

import desafio.taskmanager.task.dto.TaskPostDTO;
import desafio.taskmanager.task.dto.TaskPutDTO;
import desafio.taskmanager.task.entity.Task;
import desafio.taskmanager.task.entity.TasksForToday;
import desafio.taskmanager.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController implements TaskControllerDocs{

    private final TaskService taskService;

    @GetMapping
    public List<Task> findAll() {
        return taskService.findALl();
    }

    @GetMapping("/find_by_title")
    public List<Task> findByTitleStartingWith(@RequestParam String title) {
        return taskService.findByTitleStartingWith(title);
    }

    @GetMapping("/find_by_id/{id}")
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @GetMapping("/find_all_by_project/{id}")
    public List<Task> findAllByProject(@PathVariable Long id) {
        return taskService.findAllByProject(id);
    }

    @GetMapping("/order_by_status")
    public List<Task> findAllAndOrderByStatus() {
        return taskService.findAllAndOrderByStatus();
    }

    @GetMapping("/list_tasks_by_filters")
    public List<Task> listTasksByFilters(
            @RequestParam(name = "initialDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate initialDate,
            @RequestParam(name = "finalDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate finalDate,
            @RequestParam(name = "strict", required = false) boolean strict
    ) {
        return taskService.listTasksByFilters(initialDate, finalDate, strict);
    }

    @GetMapping("/list_tasks_for_today")
    public TasksForToday listTasksForToday(
            @RequestParam(name = "availableTime")
            @DateTimeFormat(pattern = "H:mm") LocalTime availableTime
    ) {
        return taskService.listTasksForToday(availableTime);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task save(@RequestBody @Valid TaskPostDTO taskPostDTO) {
        return taskService.save(taskPostDTO);
    }

    @PutMapping
    public Task update(@RequestBody @Valid TaskPutDTO taskPutDTO) {
        return taskService.update(taskPutDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
