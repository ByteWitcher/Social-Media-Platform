package org.sop.apigateway.feignclients;

import org.sop.apigateway.models.Reaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "POST-SERVICE", path = "/api/reaction", contextId = "reaction")
public interface PostServiceReactionClient {
    @GetMapping("/{postId}")
    List<Reaction> findByPostId(@PathVariable Long postId);

    @PostMapping("/")
    Reaction react(@RequestBody Reaction reaction);
}
