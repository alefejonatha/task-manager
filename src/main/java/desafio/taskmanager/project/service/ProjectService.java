package desafio.taskmanager.project.service;

import desafio.taskmanager.project.dto.ProjectPostRequestBody;
import desafio.taskmanager.project.entity.Project;
import desafio.taskmanager.project.mapper.ProjectMapper;
import desafio.taskmanager.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    //TODO ListAll Pageable

    public List<Project> listAllNonPageable() {
        return projectRepository.findAll();
    }

    //TODO listByTitle

    public Project findByIdOrElseThrowException(Long id){
        return projectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project not found"));
    }

    @Transactional
    public Project save(ProjectPostRequestBody projectPostRequestBody) {
        return projectRepository.save(ProjectMapper.INSTANCE.toProject(projectPostRequestBody));
    }

    //TODO update

    //TODO delete





}