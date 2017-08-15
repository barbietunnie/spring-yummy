package com.example.packtpub.yummy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping
public class SampleController {
    @RequestMapping
    public String sayTheTime() {
        return LocalDateTime.now().toString();
    }
}
