package pl.nlo.jira.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pl.nlo.jira.converters.CustomMultiPartFile;
import pl.nlo.jira.dto.UserDTO;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.mapper.UserMapper;
import pl.nlo.jira.repository.UserRepository;

import java.nio.charset.StandardCharsets;
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

    public void updateActiveUserAvatar(MultipartFile file) throws Exception {

        Optional<UserEntity> userEntity = userRepository.findById(getActiveUserDTO().getId());
        if(!userEntity.isPresent()){
            throw new Exception();
        }
        userEntity.get().setAvatar(file.getBytes());
        userRepository.save(userEntity.get());

    }

    public byte[] getAvatar() {
//        byte[] byteArray  = userRepository.findById(authenticationService.getActiveUser().getId()).get().getAvatar();
//        CustomMultiPartFile customMultiPartFile = new CustomMultiPartFile(byteArray);
//
//        return customMultiPartFile;
        return userRepository.findById(authenticationService.getActiveUser().getId()).get().getAvatar();
    }
}
