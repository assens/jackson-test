package com.foo.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class JacksonTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(JacksonTestApplication.class, args);
  }

}
