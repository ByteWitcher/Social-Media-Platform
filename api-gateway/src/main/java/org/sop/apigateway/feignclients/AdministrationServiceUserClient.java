package org.sop.apigateway.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ADMINISTRATION-SERVICE", path = "/api/user")
public interface AdministrationServiceUserClient {
    @GetMapping("/ban/{id}")
    void banUser(@PathVariable Long id);

    @GetMapping("/unban/{id}")
    void unbanUser(@PathVariable Long id);
}
