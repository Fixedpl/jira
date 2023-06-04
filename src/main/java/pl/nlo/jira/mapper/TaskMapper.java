package pl.nlo.jira.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.nlo.jira.dto.TaskDTO;
import pl.nlo.jira.entity.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "reporter.id", source = "reporterId")
    @Mapping(target = "assigned.id", source = "assignedId")
    Task toEntity(TaskDTO taskDTO);

    @Mapping(target = "reporterId", source = "reporter.id")
    @Mapping(target = "assignedId", source = "assigned.id")
    TaskDTO toDTO(Task task);

    List<TaskDTO> toDTOs(List<Task> tasks);
}
