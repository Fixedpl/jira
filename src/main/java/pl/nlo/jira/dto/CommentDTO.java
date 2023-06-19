package pl.nlo.jira.dto;

import lombok.Data;
import pl.nlo.jira.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CommentDTO {
    private Integer id;
    private String content;
    private Integer taskId;
    private String createdBy;
    private Date createdDate;
    private Date lastModifiedDate;
}
