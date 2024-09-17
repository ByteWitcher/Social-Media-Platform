package org.sop.apigateway.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ViewedPost {
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate seenAt;
    private Long userId;
    private Long postId;
}
