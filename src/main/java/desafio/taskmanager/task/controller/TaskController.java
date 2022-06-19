package desafio.taskmanager.task.controller;

import desafio.taskmanager.task.dto.TaskPostRequestBody;
import desafio.taskmanager.task.dto.TaskPutRequestBody;
import desafio.taskmanager.task.entity.Task;
import desafio.taskmanager.task.entity.TasksForToday;
import desafio.taskmanager.task.mapper.TaskMapper;
import desafio.taskmanager.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> listAllNonPageable() {
        return new ResponseEntity<>(taskService.listAllNonPageable(), HttpStatus.OK);
    }

    @GetMapping(path = "/find_task_by_title" )
    public ResponseEntity<List<Task>> findByTitleStartingWith(@RequestParam String title) {
        return new ResponseEntity<>(taskService.findByTitleStartingWith(title), HttpStatus.OK);
    }

    @GetMapping(path = "/find_task_by_id/{id}")
    public ResponseEntity<Task> findTaskByIdOrElseThrowException(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.findTaskByIdOrElseThrowException(id), HttpStatus.OK);
    }

    @GetMapping(path = "/tasks_by_project/{id}")
    public ResponseEntity<List<Task>> listTasksByProject(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.listTasksByProject(id), HttpStatus.OK);
    }

    @GetMapping(path = "/order_by_status")
    public ResponseEntity<List<Task>> findAllAndOrderByStatus() {
        return new ResponseEntity<>(taskService.findAllAndOrderByStatus(), HttpStatus.OK);
    }

    @GetMapping(path = "/list_tasks_by_filters")
    public ResponseEntity<List<Task>> listTasksByFilters(
            @RequestParam(name = "initialDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate initialDate,
            @RequestParam(name = "finalDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate finalDate,
            @RequestParam(name = "strict", required = false) boolean strict
    ) {
        return new ResponseEntity<>(taskService.listTasksByFilters(initialDate, finalDate, strict), HttpStatus.OK);
    }

    @GetMapping(path = "/list_tasks_for_today")
    public ResponseEntity<TasksForToday> listTasksForToday(
            @RequestParam(name = "availableTime")
            @DateTimeFormat(pattern = "H:mm") LocalTime availableTime
    ) {
        return new ResponseEntity<>(taskService.listTasksForToday(availableTime), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> save(@RequestBody @Valid TaskPostRequestBody taskPostRequestBody) {
        return new ResponseEntity<>(taskService.save(taskPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid TaskPutRequestBody taskPutRequestBody) {
        taskService.update(taskPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) {
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
