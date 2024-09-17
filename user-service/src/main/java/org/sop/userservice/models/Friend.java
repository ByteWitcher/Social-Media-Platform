package org.sop.userservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Friend {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String bio;
    private String image;
}
