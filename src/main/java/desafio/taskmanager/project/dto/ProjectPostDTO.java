package desafio.taskmanager.project.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPostDTO {

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String title;

}
