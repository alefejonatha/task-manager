package desafio.taskmanager.task.repository;

import desafio.taskmanager.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskCustomRepository {

    List<Task> findAllByProjectId(Long projectId);

    List<Task>findByOrderByStatusDesc();

    List<Task> findAllByOrderByPriorityAsc();

    List<Task> findByTitleStartingWith(String title);

}
