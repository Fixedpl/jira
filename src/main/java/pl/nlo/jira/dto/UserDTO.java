package pl.nlo.jira.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author marcin
 * @since 09.06.2023
 */
@Data
@Builder
public class UserDTO {
	private Integer id;
	private String email;
	private String firstName;
	private String lastName;
}
