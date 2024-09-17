package org.sop.postservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionDto {
    private Long id;
    private Long userId;
    private Long postId;
}
