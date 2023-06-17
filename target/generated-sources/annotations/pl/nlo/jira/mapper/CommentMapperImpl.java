package pl.nlo.jira.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import pl.nlo.jira.dto.CommentDTO;
import pl.nlo.jira.entity.Comment;
import pl.nlo.jira.entity.Task;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-08T13:07:46+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment toEntity(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.task( commentDTOToTask( commentDTO ) );
        comment.id( commentDTO.getId() );
        comment.content( commentDTO.getContent() );
        comment.date( commentDTO.getDate() );

        return comment.build();
    }

    @Override
    public CommentDTO toDTO(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setTaskId( commentTaskId( comment ) );
        commentDTO.setId( comment.getId() );
        commentDTO.setContent( comment.getContent() );
        commentDTO.setDate( comment.getDate() );

        return commentDTO;
    }

    protected Task commentDTOToTask(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Task.TaskBuilder task = Task.builder();

        task.id( commentDTO.getTaskId() );

        return task.build();
    }

    private Integer commentTaskId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Task task = comment.getTask();
        if ( task == null ) {
            return null;
        }
        Integer id = task.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
