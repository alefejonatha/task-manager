package desafio.taskmanager.task.mapper;

import desafio.taskmanager.task.dto.TaskPostDTO;
import desafio.taskmanager.task.dto.TaskPutDTO;
import desafio.taskmanager.task.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class TaskMapper {

    public static final TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    public abstract Task toTask(TaskPostDTO taskPostDTO);

    public abstract Task toTask(TaskPutDTO taskPutDTO);
}
