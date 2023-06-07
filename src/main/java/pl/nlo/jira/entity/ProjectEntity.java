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
@Entity(name = "project")
@Table(name = "project")
public class ProjectEntity {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@Column(length = 10000)
	private String description;

}
