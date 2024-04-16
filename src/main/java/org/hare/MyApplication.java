package org.hare;

import org.hare.framework.security.util.SecurityContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MyApplication {

    @RequestMapping("/")
    @PreAuthorize("hasRole('teacher')")
    public String hello(Authentication authentication) {
        final Long userId = SecurityContextUtils.getUserId();
        return "Hello, " + authentication.getName() + "!";
    }

    public static void main(String[] args) {
        final ConfigurableApplicationContext run = SpringApplication.run(MyApplication.class, args);
        System.out.println(run);
    }

}