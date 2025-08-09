package com.sindocker.sindocker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SindockerApplication {

    public static void main(String[] args) {
        io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv
                .configure()
                .ignoreIfMissing()
                .load();
        SpringApplication.run(SindockerApplication.class, args);
    }

}
