package pl.nlo.jira.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.nlo.jira.dto.UserDTO;
import pl.nlo.jira.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDTO userDTO);

    UserDTO toDTO(UserEntity userEntity);

    void updateUserFromDto(UserDTO userDTO, @MappingTarget UserEntity userEntity);
}
