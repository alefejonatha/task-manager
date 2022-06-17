package desafio.taskmanager.task.entity;

import desafio.taskmanager.project.entity.Project;
import desafio.taskmanager.task.enums.Priority;
import desafio.taskmanager.task.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialDate;

    @Column(nullable = false)
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate finalDate;

    @Column(nullable = false)
//    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Status status;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "project_id")
    private Project project;
}
