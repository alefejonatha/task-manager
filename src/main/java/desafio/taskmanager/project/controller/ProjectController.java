package desafio.taskmanager.project.controller;

import desafio.taskmanager.project.dto.ProjectPostDTO;
import desafio.taskmanager.project.dto.ProjectPutDTO;
import desafio.taskmanager.project.entity.Project;
import desafio.taskmanager.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController implements ProjectControllerDocs{
    private final ProjectService projectService;

    @GetMapping
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/find_by_title")
    public List<Project> findByTitleStartingWith(@RequestParam String title) {
        return projectService.findByTitleStartingWith(title);
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project save(@RequestBody @Valid ProjectPostDTO projectPostDTO) {
        return projectService.save(projectPostDTO);
    }

    @PutMapping
    public Project update(@RequestBody @Valid ProjectPutDTO projectPutDTO) {
        return projectService.update(projectPutDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }

}
