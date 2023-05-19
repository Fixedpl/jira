package pl.nlo.jira.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private Long id;

    private String description;

    private Integer reporterId;

    private Integer assignedId;

    private LocalDateTime estimatedTime;

    //TODO: Dorobic State, Sprint i Priority
    //private State stateId;

    //private Integer sprintId;

    //private Priority priorityId;
}
