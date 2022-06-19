package desafio.taskmanager.task.exception;

import javax.persistence.EntityNotFoundException;

public class TaskNotFoundException extends EntityNotFoundException {

    public TaskNotFoundException(Long id) {
        super(String.format("Task with id %s not exists!", id));
    }
}
