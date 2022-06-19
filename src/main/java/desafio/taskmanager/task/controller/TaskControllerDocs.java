package desafio.taskmanager.task.controller;

import desafio.taskmanager.task.dto.TaskPostDTO;
import desafio.taskmanager.task.dto.TaskPutDTO;
import desafio.taskmanager.task.entity.Task;
import desafio.taskmanager.task.entity.TasksForToday;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TaskControllerDocs {

    @Operation(summary = "Find all Task", description = "List all registered Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all registered Task")
    })
    List<Task> findAll();

    @Operation(summary = "Find all Task by title", description = "List of Task found by title or returns an empty list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all Task by title")
    })
    List<Task> findByTitleStartingWith(String title);

    @Operation(summary = "Find Task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    Task findById(Long id);

    @Operation(summary = "Find all Task by Project id", description = "List all Task found by Project id or returns an empty list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all Task by Project id")
    })
    List<Task> findAllByProject(Long id);

    @Operation(summary = "Find all Task and order by status", description = "List all Task and order by status ('FINISHED','UNFINISHED') or returns an empty list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request was successful")
    })
    List<Task> findAllAndOrderByStatus();

    @Operation(summary = "List Task by filters", description = "Return a list according to filters or returns an empty list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request was successful")
    })
    List<Task> listTasksByFilters(LocalDate initialDate, LocalDate finalDate, boolean strict);

    @Operation(summary = "Return a list according to available time")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a Task list"),
            @ApiResponse(responseCode = "400", description = "Unable to return a Task list")
    })
    TasksForToday listTasksForToday(LocalTime availableTime);

    @Operation(summary = "Create Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Missing mandatory fields, incorrect field range value"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    Task save(TaskPostDTO taskPostDTO);

    @Operation(summary = "Update Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task successfully updated"),
            @ApiResponse(responseCode = "400", description = "Missing mandatory fields, incorrect field range value, or the project has already been registered in the system"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    Task update(TaskPutDTO taskPutDTO);

    @Operation(summary = "Delete Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
    })
    void delete(Long id);
}


