package pl.nlo.jira.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author marcin
 * @since 06.06.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "project_sprint")
@Table(name = "project_sprint")
public class ProjectSprintEntity {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer projectId;
	private Integer sprintId;

}
