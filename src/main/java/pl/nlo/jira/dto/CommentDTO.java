package pl.nlo.jira.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Integer id;

    private String content;

    private LocalDateTime date;

    private Integer taskId;
}
