package pl.nlo.jira.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcin
 * @since 06.06.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sprint")
@Audited
@AuditOverride(forClass = Auditable.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "sprint")
public class SprintEntity extends Auditable {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer projectId;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDateTime actualStartDate;
	private LocalDateTime actualEndDate;
	private boolean isActive;

//	@ManyToMany(mappedBy = "sprints")
//	private List<Task> tasks = new ArrayList<>();
	@OneToMany(mappedBy = "sprint", fetch = FetchType.EAGER)
	private List<Task> tasks;
}
