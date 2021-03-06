package desafio.taskmanager.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import desafio.taskmanager.task.enums.Priority;
import desafio.taskmanager.task.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskPostDTO {


    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String title;

    @FutureOrPresent
    @NotNull
    private LocalDate initialDate;

    @FutureOrPresent
    @NotNull
    private LocalDate finalDate;

    @NotNull
    private LocalTime time;

    @NotNull
    private Priority priority;

    @NotNull
    private Status status;

    @NotNull
    private Long projectId;
}
