package pl.nlo.jira.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.nlo.jira.dto.CommentDTO;
import pl.nlo.jira.dto.TaskDTO;
import pl.nlo.jira.entity.Comment;
import pl.nlo.jira.entity.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "task.id", source = "taskId")
    Comment toEntity(CommentDTO commentDTO);

    @Mapping(target = "taskId", source = "task.id")
    CommentDTO toDTO(Comment comment);

    List<CommentDTO> toDTOs(List<Comment> comments);
}
