package pl.nlo.jira.service;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;
import pl.nlo.jira.dto.UserDTO;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.mapper.UserMapper;
import pl.nlo.jira.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;
    public Optional<UserEntity> getUserById(String email) {
        return userRepository.findByEmail(email);
    }
    public UserDTO getActiveUserDTO() {
        return  userRepository.findById(authenticationService.getActiveUser().getId())
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updateActiveUserDTO(UserDTO userDTO) {
         userRepository.findById(userDTO.getId())
                .map(e ->{
                    userMapper.updateUserFromDto(userDTO,e);
                 return userRepository.save(e);
                }).orElseThrow(() -> new RuntimeException("ssss"));

    }
}
