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
@Entity(name = "sprint_task")
@Table(name = "sprint_task")
public class SprintTaskLinkEntity implements Serializable {
	@Id
	private Integer sprint_id;
	@Id
	private Integer task_id;
}
