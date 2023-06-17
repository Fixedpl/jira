package pl.nlo.jira.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.nlo.jira.dto.UserDTO;
import pl.nlo.jira.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(UserEntity userEntity);
}
