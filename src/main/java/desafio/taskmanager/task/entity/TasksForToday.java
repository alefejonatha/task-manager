package desafio.taskmanager.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasksForToday {

   private LocalTime  availableTime;
   private LocalTime  totalTime;
   private List<Task> listTask;
}
