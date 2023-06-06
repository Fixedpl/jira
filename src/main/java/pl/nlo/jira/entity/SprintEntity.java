package pl.nlo.jira.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
