package desafio.taskmanager.task.dto;

import com.sun.istack.NotNull;
import desafio.taskmanager.task.enums.Priority;
import desafio.taskmanager.task.enums.Status;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TaskPutRequestBody {

    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String title;

    @NotNull
    private LocalDate initialDate;

    @NotNull
    private LocalDate finalDate;

    @NotNull
    private LocalTime time;

    @NotNull
    private Priority priority;

    @NotNull
    private Status status;

}
