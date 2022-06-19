package desafio.taskmanager.project.mapper;

import desafio.taskmanager.project.dto.ProjectPostDTO;
import desafio.taskmanager.project.dto.ProjectPutDTO;
import desafio.taskmanager.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {

    public static final ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    public abstract Project toProject(ProjectPostDTO projectPostDTO);

    public abstract Project toProject(ProjectPutDTO projectPutDTO);
}
