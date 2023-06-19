package pl.nlo.jira.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import pl.nlo.jira.dto.CommentDTO;
import pl.nlo.jira.entity.Comment;
import pl.nlo.jira.entity.Task;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-13T17:18:06+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
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
        commentDTO.setCreatedBy( comment.getCreatedBy() );
        commentDTO.setCreatedDate( comment.getCreatedDate() );
        commentDTO.setLastModifiedDate( comment.getLastModifiedDate() );

        return commentDTO;
    }

    @Override
    public List<CommentDTO> toDTOs(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentDTO> list = new ArrayList<CommentDTO>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( toDTO( comment ) );
        }

        return list;
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
