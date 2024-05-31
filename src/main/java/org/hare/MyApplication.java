package org.hare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hare
 */
@RestController
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext run = SpringApplication.run(MyApplication.class, args);
        System.out.println(run);
    }

}