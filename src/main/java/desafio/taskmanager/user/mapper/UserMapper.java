package desafio.taskmanager.user.mapper;

import desafio.taskmanager.user.dto.UserDTO;
import desafio.taskmanager.user.dto.UserPutDTO;
import desafio.taskmanager.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUser(UserDTO userDTO);

    public abstract User toUser(UserPutDTO userPutDTO);

}
