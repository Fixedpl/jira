package pl.nlo.jira.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.nlo.jira.entity.ProjectEntity;
import pl.nlo.jira.entity.ProjectUserEntity;
import pl.nlo.jira.entity.SprintEntity;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.repository.ProjectRepository;
import pl.nlo.jira.repository.ProjectUserRepository;
import pl.nlo.jira.repository.SprintRepository;
import pl.nlo.jira.repository.UserRepository;
import pl.nlo.jira.service.AuthenticationService;

import java.util.List;

/**
 * @author marcin
 * @since 06.06.2023
 */
@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

	private final ProjectRepository projectRepository;
	private final AuthenticationService authenticationService;
	private final UserRepository userRepository;
	private final ProjectUserRepository projectUserRepository;

	@GetMapping
	public List<ProjectEntity> getProjects() {
		UserEntity activeUser = authenticationService.getActiveUser();
		return projectRepository.getProjectEntityByUserId(activeUser.getId());
	}

	@GetMapping("/{id}")
	public ProjectEntity findById(@PathVariable("id") Integer id) {
		return projectRepository.findById(id).get();
	}

	@GetMapping("/{id}/members")
	public List<UserEntity> getMembers(@PathVariable("id") Integer id) {
		return userRepository.getProjectMembers(id);
	}

	@PostMapping("/{id}/members")
	public void addMember(@PathVariable("id") Integer id, UserEntity userEntity) {
		ProjectUserEntity projectUserEntity = new ProjectUserEntity();
		projectUserEntity.setProjectId(id);
		projectUserEntity.setUserId(userEntity.getId());
		projectUserRepository.save(projectUserEntity);
	}

}
