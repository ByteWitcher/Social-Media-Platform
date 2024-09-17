package org.sop.apigateway.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;
    private String phoneNumber;
    private String bio;
    private String imxage;
    private boolean enabled;
}
