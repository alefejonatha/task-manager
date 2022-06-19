package desafio.taskmanager.project.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

}
