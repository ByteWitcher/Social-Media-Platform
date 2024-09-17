package org.sop.userservice.feignclients;

import org.sop.userservice.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "API-GATEWAY", path = "/api/user")
public interface ApiGatewayUserClient {
    @GetMapping("/id/{id}")
    User findById(@PathVariable Long id);

    @DeleteMapping("/id/{id}")
    void deleteById(@PathVariable Long id);

    @GetMapping("/add-friend/{id1}/{id2}")
    boolean addFriend(@PathVariable Long id1, @PathVariable Long id2);
}

