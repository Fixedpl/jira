package pl.nlo.jira.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author marcin
 * @since 16.05.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {

	private String token;
	private String expiration;

}
