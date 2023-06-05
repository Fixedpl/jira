package pl.nlo.jira.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import pl.nlo.jira.entity.enums.Priority;
import pl.nlo.jira.entity.enums.State;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@AuditOverride(forClass = Auditable.class)
@Table(name = "tasks")
public class Task extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
    private Integer id;

    @NotNull
    private String title;

    private String description;

    @NotAudited
    @ManyToOne(cascade = CascadeType.MERGE)
    private UserEntity reporter;

    @NotAudited
    @ManyToOne(cascade = CascadeType.MERGE)
    private UserEntity assigned;

    private LocalDateTime estimatedTime;

    //TODO: Dorobic Sprint
    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    //private Sprint sprint;

}
