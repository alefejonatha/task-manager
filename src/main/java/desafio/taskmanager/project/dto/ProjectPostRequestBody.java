package desafio.taskmanager.project.dto;

import com.sun.istack.NotNull;
import desafio.taskmanager.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPostRequestBody {

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String title;

//    private List<Task> tasks;
}
