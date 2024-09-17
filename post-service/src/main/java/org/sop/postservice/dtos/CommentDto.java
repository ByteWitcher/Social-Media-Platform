package org.sop.postservice.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String text;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;
    private Long userId;
    private Long postId;

}
