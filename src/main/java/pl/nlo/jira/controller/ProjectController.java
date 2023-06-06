package pl.nlo.jira.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.nlo.jira.entity.ProjectEntity;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.repository.ProjectRepository;
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

	@GetMapping
	public List<ProjectEntity> getProjects() {
		UserEntity activeUser = authenticationService.getActiveUser();
		return projectRepository.getProjectEntityByUserId(activeUser.getId());
	}

}
