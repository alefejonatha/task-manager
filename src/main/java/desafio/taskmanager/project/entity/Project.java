package desafio.taskmanager.project.entity;

import desafio.taskmanager.task.entity.Task;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

//    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
//    private List<Task> tasks;
}
