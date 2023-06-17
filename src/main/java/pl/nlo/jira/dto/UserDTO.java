package pl.nlo.jira.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Integer id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String location;

    private String organizationName;

    private Byte[] avatar;

    private String phoneNumber;

    private LocalDate birthday;
}
