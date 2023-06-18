package pl.nlo.jira.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nlo.jira.dto.UserDTO;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.request.authentication.AuthenticationRequest;
import pl.nlo.jira.response.authentication.AuthenticationResponse;
import pl.nlo.jira.service.AuthenticationService;
import pl.nlo.jira.request.register.RegisterRequest;

/**
 * @author marcin
 * @since 15.03.2023
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody RegisterRequest request
	) {
		return ResponseEntity.ok(authenticationService.register(request));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest request
	) {
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}


}
