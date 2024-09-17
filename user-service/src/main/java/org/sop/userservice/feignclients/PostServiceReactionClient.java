package org.sop.userservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "POST-SERVICE", path = "/api/reaction", contextId = "reaction")
public interface PostServiceReactionClient {
    @DeleteMapping("/{userId}")
    void deleteByUserId(@PathVariable Long userId);
}
