package pl.nlo.jira.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nlo.jira.dto.ProjectDTO;
import pl.nlo.jira.entity.ProjectEntity;
import pl.nlo.jira.entity.SprintEntity;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.repository.ProjectRepository;
import pl.nlo.jira.repository.SprintRepository;
import pl.nlo.jira.service.AuthenticationService;
import pl.nlo.jira.service.ProjectService;

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
	private final SprintRepository sprintRepository;
	private final ProjectService projectService;

	@PostMapping("/create")
	public ResponseEntity<Void> createProject(@RequestBody ProjectDTO projectDTO) {
		projectService.createProject(projectDTO);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public List<ProjectEntity> getProjects() {
		UserEntity activeUser = authenticationService.getActiveUser();
		return projectRepository.getProjectEntityByUserId(activeUser.getId());
	}

	@GetMapping("/{id}")
	public ProjectEntity findById(@PathVariable("id") Integer id) {
		return projectRepository.findById(id).get();
	}

	@GetMapping("/{id}/sprint")
	public List<SprintEntity> getSprints(@PathVariable("id") Integer id) {
		return sprintRepository.findByProjectId(id);
	}

	@PostMapping("/{id}/sprint")
	public void addSprint(@PathVariable("id") Integer id, @RequestBody SprintEntity sprintEntity) {
		sprintEntity.setProjectId(id);
		sprintRepository.save(sprintEntity);
	}

	@GetMapping("/usersProject")
	public ResponseEntity<List<ProjectEntity>> getCurrentUserProjects() {
		return ResponseEntity.ok(projectService.getCurrentUserProjects());
	}
}
