package pl.nlo.jira.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nlo.jira.converters.UserConverter;
import pl.nlo.jira.dto.UserDTO;
import pl.nlo.jira.entity.ProjectUserEntity;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.repository.ProjectUserRepository;
import pl.nlo.jira.repository.UserRepository;
import pl.nlo.jira.service.AuthenticationService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author marcin
 * @since 06.06.2023
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final UserRepository userRepository;
	private final ProjectUserRepository projectUserRepository;
	private final UserConverter userConverter;
	private final AuthenticationService authenticationService;

	@GetMapping("/{id}")
	public UserDTO getUser(@PathVariable("id") Integer id) {
		UserEntity userEntity = userRepository.findById(id)
				.get();
		return userConverter.convert(userEntity);
	}

	@PostMapping
	public void editUser(@RequestBody UserEntity userEntity) {
		// TODO
	}

	@GetMapping("/{projectId}/members")
	public List<UserDTO> getProjectMembers(@PathVariable("projectId") Integer projectId) {
		List<UserEntity> test = userRepository.getProjectMembers(projectId);
		return test
				.stream()
				.map(userConverter::convert)
				.collect(Collectors.toList());
	}

	@GetMapping("/{sprintId}/sprintMembers")
	public List<UserDTO> getProjectMembersBySprintId(@PathVariable("sprintId") Integer sprintId) {
		List<UserEntity> test = userRepository.getProjectMembersBySprintId(sprintId);
		return test
				.stream()
				.map(userConverter::convert)
				.collect(Collectors.toList());
	}

	@PostMapping("/{projectId}/members")
	public void addMember(@PathVariable("projectId") Integer projectId, @RequestParam("email") String email) {
		UserEntity userEntity = userRepository.findByEmail(email).get();

		ProjectUserEntity projectUserEntity = new ProjectUserEntity();
		projectUserEntity.setProjectId(projectId);
		projectUserEntity.setUserId(userEntity.getId());
		projectUserRepository.save(projectUserEntity);
	}

	@GetMapping("/getUser")
	public ResponseEntity<UserEntity> getCurrentUser() {
		return ResponseEntity.ok(authenticationService.getActiveUser());
	}

}
