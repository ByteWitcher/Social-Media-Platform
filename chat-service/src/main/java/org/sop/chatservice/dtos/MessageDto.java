package org.sop.chatservice.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MessageDto {
    private Long id;
    private String message;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate sentAt;
    private Long senderId;
    private Long receiverId;
}
