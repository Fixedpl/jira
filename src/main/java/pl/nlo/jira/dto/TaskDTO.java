package pl.nlo.jira.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.nlo.jira.entity.SprintEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskDTO {
    private Long id;

    private String title;

    private String description;

    private Integer reporterId;

    private Integer assignedId;

    private LocalDateTime estimatedTime;

    //TODO: Dorobic State, Sprint i Priority
    private String state;

    private String priority;

    private Integer sprintId;
}
