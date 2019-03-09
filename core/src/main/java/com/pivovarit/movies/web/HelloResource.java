package com.pivovarit.movies.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
class HelloResource {

    @Value("${hello.message: Hello World!}")
    private String helloString;

    @GetMapping("/hello")
    String hello() {
        return helloString;
    }
}
