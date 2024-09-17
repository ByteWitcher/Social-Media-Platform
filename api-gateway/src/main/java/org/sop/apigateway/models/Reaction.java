package org.sop.apigateway.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reaction {
    private Long id;
    private Long userId;
    private Long postId;
}
