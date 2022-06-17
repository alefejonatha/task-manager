package desafio.taskmanager.task.repository;

import desafio.taskmanager.task.entity.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskCustomRepository {

    List<Task> listTasksByFilters(LocalDate initialDate);
}
