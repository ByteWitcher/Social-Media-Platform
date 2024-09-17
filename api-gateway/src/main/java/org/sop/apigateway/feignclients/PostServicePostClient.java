package org.sop.apigateway.feignclients;

import org.sop.apigateway.models.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "POST-SERVICE", path = "/api/post", contextId = "post")
public interface PostServicePostClient {
    @GetMapping("/{userId}")
    List<Post> findByUserId(@PathVariable Long userId);

    @GetMapping("/{userId}/{page}/{size}")
    List<Post> findUnviewedPostsByUserId(@PathVariable Long userId, @PathVariable int page, @PathVariable int size);

    @DeleteMapping("/id/{id}")
    void deleteById(@PathVariable Long id);

    @PostMapping("/")
    Post save(@RequestBody Post post);

    @PutMapping("/")
    Post update(@RequestBody Post post);
}
