package pl.nlo.jira.testing_env;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.nlo.jira.entity.ProjectEntity;
import pl.nlo.jira.entity.ProjectUserLinkEntity;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.entity.enums.RoleEnum;
import pl.nlo.jira.repository.ProjectRepository;
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
	private final ProjectRepository projectRepository;

	@PostConstruct
	@Transactional
	public void init() {
		UserEntity admin = addAdmin();
		ProjectEntity pacman = addProjectPacman();
		addAdminToProjectPacman(admin, pacman);
	}

	private UserEntity addAdmin() {
		UserEntity userEntity = UserEntity.builder()
				.email("admin")
				.password(passwordEncoder.encode("admin"))
				.roleEnum(RoleEnum.ADMIN)
				.build();
		return userRepository.save(userEntity);
	}

	private ProjectEntity addProjectPacman() {
		ProjectEntity projectEntity = ProjectEntity.builder()
				.name("Pacman")
				.description("słynna gra zręcznościowa autorstwa Namco polegająca na sterowaniu małego stworka w celu zdobywania pożywanie (a tym samym i punktów) oraz uciekania przed czterema duszkami. Gra jest podzielona na poziomy, które zmieniają się po zjedzeniu wszystkich elementów w grze. Gra doczekała się wielu odmian. Gra zadebiutowała w 1980 roku. Projektantem gry był Tōru Iwatani.")
				.build();
		return projectRepository.save(projectEntity);
	}

	private void addAdminToProjectPacman(UserEntity admin, ProjectEntity pacman) {
		// TODO
	}

}
