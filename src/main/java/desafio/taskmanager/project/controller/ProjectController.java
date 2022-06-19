package desafio.taskmanager.project.controller;

import desafio.taskmanager.project.dto.ProjectPostRequestBody;
import desafio.taskmanager.project.dto.ProjectPutRequestBody;
import desafio.taskmanager.project.entity.Project;
import desafio.taskmanager.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> findAllNonPageable() {
        return new ResponseEntity<>(projectService.listAllNonPageable(), HttpStatus.OK);
    }

    @GetMapping(path = "/find_by_title")
    public ResponseEntity<List<Project>> findByTitleStartingWith(@RequestParam String title){
        return new ResponseEntity<>(projectService.findByTitleStartingWith(title), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Project> findByIdOrElseThrowException(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.findByIdOrElseThrowException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> save(@RequestBody @Valid ProjectPostRequestBody projectPostRequestBody) {
        return new ResponseEntity<>(projectService.save(projectPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid ProjectPutRequestBody projectPutRequestBody){
        projectService.update(projectPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
