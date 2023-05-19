package pl.nlo.jira.response.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.nlo.jira.security.jwt.JwtToken;

import java.util.Date;

/**
 * @author marcin
 * @since 15.03.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

	private JwtToken token;

}
