package pl.nlo.jira.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.entity.enums.RoleEnum;
import pl.nlo.jira.repository.UserRepository;
import pl.nlo.jira.request.authentication.AuthenticationRequest;
import pl.nlo.jira.request.register.RegisterRequest;
import pl.nlo.jira.response.authentication.AuthenticationResponse;
import pl.nlo.jira.security.jwt.JwtService;
import pl.nlo.jira.security.jwt.JwtToken;

/**
 * @author marcin
 * @since 15.03.2023
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;



	public AuthenticationResponse register(RegisterRequest request) {
		UserEntity userEntity = UserEntity.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.roleEnum(RoleEnum.USER)
				.build();
		userRepository.save(userEntity);
		JwtToken jwtToken = jwtService.generateToken(userEntity);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
				)
		);

		UserEntity userEntity = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Nie znaleziono użytkownika"));
		JwtToken jwtToken = jwtService.generateToken(userEntity);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	public UserEntity getActiveUser() {
		return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}




}
