package pl.nlo.jira.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nlo.jira.dto.AccountDTO;
import pl.nlo.jira.dto.UserDTO;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.service.AccountService;

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
}
