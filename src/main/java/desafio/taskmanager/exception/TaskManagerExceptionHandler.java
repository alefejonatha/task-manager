package desafio.taskmanager.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class TaskManagerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
        return responseEntity(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                Collections.singletonList(exception.getMessage())
        );
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityExistsException exception) {
        return responseEntity(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                Collections.singletonList(exception.getMessage())
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> listErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> listErrors.add("Field " + fieldError.getField() + " " + fieldError.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(objectError -> listErrors.add("Object" + objectError.getObjectName() + " " + objectError.getDefaultMessage()));

        return responseEntity(HttpStatus.BAD_REQUEST, "Informed argument(s) validation error(s)", listErrors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return responseEntity(HttpStatus.BAD_REQUEST,
                "Malformed JSON Error or field error when using API",
                Collections.singletonList(ex.getLocalizedMessage())
        );
    }

    private ResponseEntity<Object> responseEntity(HttpStatus httpStatus, String message, List<String> erros) {
        ErrorModel errorModel = ErrorModel.builder()
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .message(message)
                .errors(erros)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(httpStatus).body(errorModel);
    }
}
