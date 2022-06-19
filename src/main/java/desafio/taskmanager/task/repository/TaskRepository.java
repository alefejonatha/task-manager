package desafio.taskmanager.task.repository;

import desafio.taskmanager.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskCustomRepository {

    @Query(value = "select p from Task p join Project c on p.project.id = c.id where c.id = :projectId")
    List<Task> getTasksByProject(@Param("projectId") Long projectId);

    @Query("Select b from Task as b order by b.status desc")
    List<Task> findAllOrderByStatus();

    @Query("Select b from Task as b order by b.priority asc")
    List<Task> findAllOrderByPriority();

    //TODO findAllByOrderByIdAsc

    List<Task> findByTitleStartingWith(String title);

}
