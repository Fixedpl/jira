package pl.nlo.jira.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.nlo.jira.dto.AccountDTO;
import pl.nlo.jira.dto.UserDTO;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.service.AccountService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<UserEntity> getUserByEmail(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity(accountService.getUserById(accountDTO.getEmail()), HttpStatus.OK);
    }
    @GetMapping("/user")
    public UserDTO getUserInfo(){
        return accountService.getActiveUserDTO();
    }
    @PostMapping("/user")
    public ResponseEntity<Void> updateUserInfo(@RequestBody UserDTO userDTO){
        accountService.updateActiveUserDTO(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @PostMapping("/user/avatar")
//    public ResponseEntity<Void> updateUserAvatar(@RequestParam("myFile")MultipartFile file) throws Exception {
//        accountService.updateActiveUserAvatar(file);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    @GetMapping("/user/avatar")
    public byte [] getAvatar(){
        return accountService.getAvatar();
        //TODO zmienić ten getmapping tak zeby wysyłał ten MultipartFile albo sprawdzić co to za jebany bytes który jest jakimś pierdolonym nullem

    }
}
