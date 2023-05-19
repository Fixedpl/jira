package pl.nlo.jira.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import pl.nlo.jira.dto.TaskDTO;
import pl.nlo.jira.entity.Task;
import pl.nlo.jira.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-19T17:14:50+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task toEntity(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task.TaskBuilder task = Task.builder();

        task.reporter( taskDTOToUserEntity( taskDTO ) );
        task.assigned( taskDTOToUserEntity1( taskDTO ) );
        if ( taskDTO.getId() != null ) {
            task.id( taskDTO.getId().intValue() );
        }
        task.description( taskDTO.getDescription() );
        task.estimatedTime( taskDTO.getEstimatedTime() );

        return task.build();
    }

    @Override
    public TaskDTO toDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setReporterId( taskReporterId( task ) );
        taskDTO.setAssignedId( taskAssignedId( task ) );
        if ( task.getId() != null ) {
            taskDTO.setId( task.getId().longValue() );
        }
        taskDTO.setDescription( task.getDescription() );
        taskDTO.setEstimatedTime( task.getEstimatedTime() );

        return taskDTO;
    }

    @Override
    public List<TaskDTO> toDTOs(List<Task> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<TaskDTO> list = new ArrayList<TaskDTO>( tasks.size() );
        for ( Task task : tasks ) {
            list.add( toDTO( task ) );
        }

        return list;
    }

    protected UserEntity taskDTOToUserEntity(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( taskDTO.getReporterId() );

        return userEntity.build();
    }

    protected UserEntity taskDTOToUserEntity1(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( taskDTO.getAssignedId() );

        return userEntity.build();
    }

    private Integer taskReporterId(Task task) {
        if ( task == null ) {
            return null;
        }
        UserEntity reporter = task.getReporter();
        if ( reporter == null ) {
            return null;
        }
        Integer id = reporter.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer taskAssignedId(Task task) {
        if ( task == null ) {
            return null;
        }
        UserEntity assigned = task.getAssigned();
        if ( assigned == null ) {
            return null;
        }
        Integer id = assigned.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
