package org.sop.apigateway;

import org.sop.apigateway.security.models.ERole;
import org.sop.apigateway.security.models.Role;
import org.sop.apigateway.security.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            List<Role> roles = roleRepository.findAll();
            if (roles.isEmpty()) {
                Role roleAdmin = new Role(ERole.ROLE_ADMIN);
                Role roleUser = new Role(ERole.ROLE_USER);
                roleRepository.save(roleAdmin);
                roleRepository.save(roleUser);
            }
        };
    }
}
