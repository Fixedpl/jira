package pl.nlo.jira.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.nlo.jira.dto.TaskDTO;
import pl.nlo.jira.entity.SprintEntity;
import pl.nlo.jira.entity.Task;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "reporter.id", source = "reporterId")
    @Mapping(target = "assigned.id", source = "assignedId")
            @Mapping(target = "sprint.id", source = "sprintId")
    //@Mapping(target = "sprints", source = "sprintId", qualifiedByName = "mapToSprintList")
    Task toEntity(TaskDTO taskDTO);

    @Mapping(target = "reporterId", source = "reporter.id")
    @Mapping(target = "assignedId", source = "assigned.id")
    TaskDTO toDTO(Task task);

    List<TaskDTO> toDTOs(List<Task> tasks);

    @Named("mapToSprintList")
    static List<SprintEntity> mapToSprintList(Integer sprintId) {
        if (sprintId == null) {
            return null;
        }

        SprintEntity sprintEntity = new SprintEntity();
        sprintEntity.setId(sprintId);

        return Collections.singletonList(sprintEntity);
    }
}
