package pl.nlo.jira.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.nlo.jira.dto.ProjectDTO;
import pl.nlo.jira.entity.ProjectEntity;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    ProjectEntity toEntity(ProjectDTO taskDTO);

    ProjectDTO toDTO(ProjectEntity project);
}
