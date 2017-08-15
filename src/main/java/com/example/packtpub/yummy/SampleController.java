package com.example.packtpub.yummy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping
public class SampleController {
    @RequestMapping
    public @ResponseBody String sayTheTime() {
        return LocalDateTime.now().toString();
    }
}
