package pl.nlo.jira.dto;

import lombok.Data;

import java.time.LocalDateTime;

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

    //private Integer sprintId;

}
