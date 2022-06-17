package desafio.taskmanager.project.mapper;

import desafio.taskmanager.project.dto.ProjectPostRequestBody;
import desafio.taskmanager.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {

    public static final ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    public abstract Project toProject(ProjectPostRequestBody projectPostRequestBody);

    //TODO PUTREQUESTBODY
}
