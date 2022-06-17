package desafio.taskmanager.task.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    FINISHED("FINISHED"),
    UNFINISHED("UNFINISHED");

    private final String description;
}
