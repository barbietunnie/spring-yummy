package com.example.packtpub.yummy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping
public class SampleController {
    @RequestMapping
    public LocalDateTime sayTheTime() {
        return LocalDateTime.now();
    }

    @RequestMapping("many")
    public String sayTheTimeManyTimes(@RequestParam() String name, @RequestParam int repetitions) {
        return IntStream.rangeClosed(1, repetitions)
                        .mapToObj(i -> i + ". Hello, " + name + "! Now it is " + LocalDateTime.now())
                        .collect(Collectors.joining("\n"));
    }
}
