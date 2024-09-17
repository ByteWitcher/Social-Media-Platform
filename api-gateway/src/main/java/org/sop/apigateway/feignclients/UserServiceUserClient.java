package org.sop.apigateway.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", path = "/api/user", contextId = "user")
public interface UserServiceUserClient {
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id);
}
