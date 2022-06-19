package desafio.taskmanager.project.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ProjectPutRequestBody {

    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String title;
}
