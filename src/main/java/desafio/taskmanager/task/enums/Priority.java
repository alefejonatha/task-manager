package desafio.taskmanager.task.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Priority {

    HIGH("HIGH"),
    LOW("LOW");

    private final String description;
}
