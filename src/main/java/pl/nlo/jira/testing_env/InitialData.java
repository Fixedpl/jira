package pl.nlo.jira.testing_env;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.nlo.jira.entity.ProjectEntity;
import pl.nlo.jira.entity.ProjectUserEntity;
import pl.nlo.jira.entity.SprintEntity;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.entity.enums.RoleEnum;
import pl.nlo.jira.repository.ProjectRepository;
import pl.nlo.jira.repository.ProjectUserRepository;
import pl.nlo.jira.repository.SprintRepository;
import pl.nlo.jira.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Profile("test")
@AllArgsConstructor
public class InitialData {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final ProjectUserRepository projectUserRepository;
	private final SprintRepository sprintRepository;

	@PostConstruct
	@Transactional
	public void init() {
		UserEntity admin = addAdmin();
		ProjectEntity pacman = addProjectPacman();
		addAdminToProjectPacman(admin, pacman);
		addSprintsToPacman(pacman);
		addPacmanTeam(pacman);
	}

	private UserEntity addAdmin() {
		UserEntity userEntity = UserEntity.builder()
				.email("admin@gmail.com")
				.password(passwordEncoder.encode("admin"))
				.firstName("Arnold")
				.lastName("Admin")
				.roleEnum(RoleEnum.ADMIN)
				.build();
		return userRepository.save(userEntity);
	}

	private ProjectEntity addProjectPacman() {
		ProjectEntity projectEntity = ProjectEntity.builder()
				.name("Pacman")
				.description("Słynna gra zręcznościowa autorstwa Namco polegająca na sterowaniu małego stworka w celu zdobywania pożywanie (a tym samym i punktów) oraz uciekania przed czterema duszkami. Gra jest podzielona na poziomy, które zmieniają się po zjedzeniu wszystkich elementów w grze. Gra doczekała się wielu odmian. Gra zadebiutowała w 1980 roku. Projektantem gry był Tōru Iwatani.")
				.build();
		return projectRepository.save(projectEntity);
	}

	private void addAdminToProjectPacman(UserEntity admin, ProjectEntity pacman) {
		ProjectUserEntity projectUserEntity = new ProjectUserEntity();
		projectUserEntity.setProjectId(pacman.getId());
		projectUserEntity.setUserId(admin.getId());
		projectUserRepository.save(projectUserEntity);
	}

	private void addSprintsToPacman(ProjectEntity pacman) {
		LocalDate currentDate = LocalDate.now();
		LocalDateTime currentDatetime = LocalDateTime.now();

		SprintEntity sprintEntity1 = new SprintEntity();
		sprintEntity1.setProjectId(pacman.getId());
		sprintEntity1.setTitle("Sprint 1 - Wymagania");
		sprintEntity1.setActive(false);
		sprintEntity1.setStartDate(currentDate.minusDays(8));
		sprintEntity1.setEndDate(currentDate.minusDays(1));
		sprintEntity1.setActualStartDate(currentDatetime.minusDays(7));
		sprintEntity1.setActualEndDate(currentDatetime.minusDays(1));

		SprintEntity sprintEntity2 = new SprintEntity();
		sprintEntity2.setProjectId(pacman.getId());
		sprintEntity2.setTitle("Sprint 2 - Projekt implementacji");
		sprintEntity2.setActive(true);
		sprintEntity2.setStartDate(currentDate.minusDays(0));
		sprintEntity2.setEndDate(currentDate.plusDays(6));
		sprintEntity2.setActualStartDate(currentDatetime);
		sprintEntity2.setActualEndDate(null);

		SprintEntity sprintEntity3 = new SprintEntity();
		sprintEntity3.setProjectId(pacman.getId());
		sprintEntity3.setTitle("Sprint 3 - Implementacja");
		sprintEntity3.setActive(false);
		sprintEntity3.setStartDate(currentDate.minusDays(8));
		sprintEntity3.setEndDate(currentDate.minusDays(1));
		sprintEntity3.setActualStartDate(null);
		sprintEntity3.setActualEndDate(null);

		SprintEntity sprintEntity4 = new SprintEntity();
		sprintEntity4.setProjectId(pacman.getId());
		sprintEntity4.setTitle("Sprint 4 - Implementacja kontynuacja");
		sprintEntity4.setActive(false);
		sprintEntity4.setStartDate(currentDate.plusDays(8));
		sprintEntity4.setEndDate(currentDate.plusDays(14));
		sprintEntity4.setActualStartDate(null);
		sprintEntity4.setActualEndDate(null);

		SprintEntity sprintEntity5 = new SprintEntity();
		sprintEntity5.setProjectId(pacman.getId());
		sprintEntity5.setTitle("Sprint 5 - Testy");
		sprintEntity5.setActive(false);
		sprintEntity5.setStartDate(currentDate.plusDays(15));
		sprintEntity5.setEndDate(currentDate.plusDays(21));
		sprintEntity5.setActualStartDate(null);
		sprintEntity5.setActualEndDate(null);

		sprintRepository.save(sprintEntity1);
		sprintRepository.save(sprintEntity2);
		sprintRepository.save(sprintEntity3);
		sprintRepository.save(sprintEntity4);
		sprintRepository.save(sprintEntity5);
	}

	private void addPacmanTeam(ProjectEntity pacman) {
		UserEntity teamLeader = UserEntity.builder()
				.email("szef@gmail.com")
				.password(passwordEncoder.encode("szef"))
				.firstName("Rafał")
				.lastName("Żurkowski")
				.roleEnum(RoleEnum.USER)
				.build();

		UserEntity frontEndDeveloper = UserEntity.builder()
				.email("frontend@gmail.com")
				.password(passwordEncoder.encode("frontend"))
				.firstName("Michał")
				.lastName("Pavczenko")
				.roleEnum(RoleEnum.USER)
				.build();

		UserEntity backEndDeveloper = UserEntity.builder()
				.email("backend@gmail.com")
				.password(passwordEncoder.encode("backend"))
				.firstName("Bogdan")
				.lastName("Patheon")
				.roleEnum(RoleEnum.USER)
				.build();

		UserEntity analyst = UserEntity.builder()
				.email("analyst@gmail.com")
				.password(passwordEncoder.encode("analyst"))
				.firstName("Dora")
				.lastName("JPEG")
				.roleEnum(RoleEnum.USER)
				.build();

		UserEntity designer = UserEntity.builder()
				.email("designer@gmail.com")
				.password(passwordEncoder.encode("designer"))
				.firstName("Gucio")
				.lastName("Lolek")
				.roleEnum(RoleEnum.USER)
				.build();

		UserEntity dodawany = UserEntity.builder()
				.email("dodawany@gmail.com")
				.password(passwordEncoder.encode("dodawany"))
				.firstName("Żaneta")
				.lastName("Schab")
				.roleEnum(RoleEnum.USER)
				.build();

		userRepository.save(teamLeader);
		userRepository.save(frontEndDeveloper);
		userRepository.save(backEndDeveloper);
		userRepository.save(analyst);
		userRepository.save(designer);
		userRepository.save(dodawany);

		ProjectUserEntity teamLeaderLink = ProjectUserEntity.builder()
				.projectId(pacman.getId())
				.userId(teamLeader.getId())
				.build();

		ProjectUserEntity frontEndDeveloperLink = ProjectUserEntity.builder()
				.projectId(pacman.getId())
				.userId(frontEndDeveloper.getId())
				.build();

		ProjectUserEntity backEndDeveloperLink = ProjectUserEntity.builder()
				.projectId(pacman.getId())
				.userId(backEndDeveloper.getId())
				.build();

		ProjectUserEntity analystLink = ProjectUserEntity.builder()
				.projectId(pacman.getId())
				.userId(analyst.getId())
				.build();

		ProjectUserEntity designerLink = ProjectUserEntity.builder()
				.projectId(pacman.getId())
				.userId(designer.getId())
				.build();

		projectUserRepository.save(teamLeaderLink);
		projectUserRepository.save(frontEndDeveloperLink);
		projectUserRepository.save(backEndDeveloperLink);
		projectUserRepository.save(analystLink);
		projectUserRepository.save(designerLink);
	}

}
