package pl.nlo.jira.testing_env;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.entity.enums.RoleEnum;
import pl.nlo.jira.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * @author marcin
 * @since 04.06.2023
 */
@Component
@Profile("test")
@AllArgsConstructor
public class InitialData {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@PostConstruct
	public void init() {
		addAdmin();
	}

	private void addAdmin() {
		UserEntity userEntity = UserEntity.builder()
				.email("admin")
				.password(passwordEncoder.encode("admin"))
				.roleEnum(RoleEnum.ADMIN)
				.build();
		userRepository.save(userEntity);
	}
}
