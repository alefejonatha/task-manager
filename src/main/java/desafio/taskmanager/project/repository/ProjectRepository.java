package desafio.taskmanager.project.repository;

import desafio.taskmanager.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByTitleStartingWith(String title);
    Optional<Project> findByTitle(String title);

}
