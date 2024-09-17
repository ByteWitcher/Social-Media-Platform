package org.sop.userservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "POST-SERVICE", path = "/api/post", contextId = "post")
public interface PostServicePostClient {
    @DeleteMapping("/user-id/{userId}")
    void deleteByUserId(@PathVariable Long userId);
}
