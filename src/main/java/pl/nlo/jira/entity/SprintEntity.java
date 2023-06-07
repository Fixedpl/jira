package pl.nlo.jira.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author marcin
 * @since 06.06.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sprint")
@Table(name = "sprint")
public class SprintEntity {

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

}
