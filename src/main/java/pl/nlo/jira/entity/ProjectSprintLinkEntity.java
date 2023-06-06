package pl.nlo.jira.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
public class ProjectSprintLinkEntity implements Serializable {

	@Id
	private Integer project_id;
	@Id
	private Integer sprint_id;

}
