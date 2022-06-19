package desafio.taskmanager.project.service;

import desafio.taskmanager.project.dto.ProjectPostDTO;
import desafio.taskmanager.project.dto.ProjectPutDTO;
import desafio.taskmanager.project.entity.Project;
import desafio.taskmanager.project.exception.ProjectAlreadyExistsException;
import desafio.taskmanager.project.exception.ProjectNotFoundException;
import desafio.taskmanager.project.mapper.ProjectMapper;
import desafio.taskmanager.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;


    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public List<Project> findByTitleStartingWith(String title) {
        return projectRepository.findByTitleStartingWith(title);
    }

    public Project findById(Long id) {
        return verifyAndGet(id);
    }

    @Transactional
    public Project save(ProjectPostDTO projectPostDTO) {
        verifyIfExists(projectPostDTO.getTitle());
        return projectRepository.save(ProjectMapper.INSTANCE.toProject(projectPostDTO));
    }

    public Project update(ProjectPutDTO projectPutDTO) {
        findById(projectPutDTO.getId());
        verifyIfExists(projectPutDTO.getTitle());
        return projectRepository.save(ProjectMapper.INSTANCE.toProject(projectPutDTO));
    }

    public void delete(Long id) {
        projectRepository.delete(verifyAndGet(id));
    }

    public Project verifyAndGet(Long id) {
        Project foundProject = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
        return foundProject;
    }

    private void verifyIfExists(String title) {
        projectRepository.findByTitle(title)
                .ifPresent(project -> {
                    throw new ProjectAlreadyExistsException(title);
                });
    }


}
