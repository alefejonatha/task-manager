package desafio.taskmanager.user.exception;

import javax.persistence.EntityExistsException;

public class UserAlreadyExistsException extends EntityExistsException {

    public UserAlreadyExistsException(String username) {
        super(String.format("User with username %s already exists!", username));
    }
}
