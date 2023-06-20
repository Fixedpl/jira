package pl.nlo.jira.entity;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	//TODO dodac total tasks i id usera i pporawić wysyłanie informacji do bazy i odbieranie ich
}
