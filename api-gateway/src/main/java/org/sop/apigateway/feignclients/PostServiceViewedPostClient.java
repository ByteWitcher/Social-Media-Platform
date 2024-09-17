package org.sop.apigateway.feignclients;

import org.sop.apigateway.models.ViewedPost;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "POST-SERVICE", path = "/api/viewed-post", contextId = "viewed-post")
public interface PostServiceViewedPostClient {
    @GetMapping("/{userId}")
    List<ViewedPost> findByUserId(@PathVariable Long userId);

    @PostMapping("/")
    ViewedPost save(@RequestBody ViewedPost viewedPost);
}
