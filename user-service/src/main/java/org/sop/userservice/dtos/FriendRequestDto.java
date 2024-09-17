package org.sop.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FriendRequestDto {
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate sentAt;
    private Long senderId;
    private Long receiverId;
}
