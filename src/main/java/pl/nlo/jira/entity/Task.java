package pl.nlo.jira.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import pl.nlo.jira.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @ManyToOne
    private UserEntity reporter;

    @ManyToOne
    private UserEntity assigned;

    private LocalDateTime estimatedTime;

    //TODO: Dorobic State, Sprint i Priority
    //private State state;

    //private Sprint sprint;

    //private Priority priority;
}
