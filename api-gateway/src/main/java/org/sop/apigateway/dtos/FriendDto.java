package org.sop.apigateway.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String bio;
    private String image;
}
