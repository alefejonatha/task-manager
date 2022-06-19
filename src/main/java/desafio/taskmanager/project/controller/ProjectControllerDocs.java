package desafio.taskmanager.project.controller;

import desafio.taskmanager.project.dto.ProjectPostDTO;
import desafio.taskmanager.project.dto.ProjectPutDTO;
import desafio.taskmanager.project.entity.Project;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

public interface ProjectControllerDocs {

    @Operation(summary = "Find all", description = "List all registered Projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all registered Projects")
    })
    List<Project> findAll();

    @Operation(summary = "Find all by title", description = "List of projects found by title or returns an empty list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all registered Projects by title")
    })
    List<Project> findByTitleStartingWith(String title);

    @Operation(summary = "Find project by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project found successfully"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    Project findById(Long id);

    @Operation(summary = "Create Project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Project created successfully"),
            @ApiResponse(responseCode = "400", description = "Missing mandatory fields, incorrect field range value, or the project has already been registered in the system")
    })
    Project save(ProjectPostDTO projectPostDTO);


    @Operation(summary = "Update Project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project successfully updated"),
            @ApiResponse(responseCode = "404", description = "Project not found"),
            @ApiResponse(responseCode = "400", description = "Missing mandatory fields, incorrect field range value")
    })
    Project update(ProjectPutDTO projectPutDTO);

    @Operation(summary = "Delete Project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Project deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Project not found"),
    })
    void delete(Long id);
}
