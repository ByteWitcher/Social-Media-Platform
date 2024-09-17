package org.sop.apigateway.controllers;

import org.sop.apigateway.feignclients.PostServiceReactionClient;
import org.sop.apigateway.models.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-service/api/reaction")
public class PostServiceReactionController {
    @Autowired
    private PostServiceReactionClient postServiceReactionClient;

    @GetMapping("/{postId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Reaction> findByPostId(@PathVariable Long postId) {
        return postServiceReactionClient.findByPostId(postId);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Reaction react(@RequestBody Reaction reaction) {
        return postServiceReactionClient.react(reaction);
    }
}
