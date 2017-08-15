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
    public String sayTheTimeManyTimes(@RequestParam() String name, @RequestParam(defaultValue = "10") int repetitions) {
        return IntStream.rangeClosed(1, repetitions)
                        .mapToObj(i -> i + ". Hello, " + name + "! Now it is " + LocalDateTime.now())
                        .collect(Collectors.joining("\n"));
    }

    @RequestMapping("manyParams")
    public String sayTheTimeManyParams(Params params) {
        return IntStream.rangeClosed(1, params.getRepetitions())
                .mapToObj(i -> i + ". Hello, " + params.getName() + "! Now it is " + LocalDateTime.now())
                .collect(Collectors.joining("\n"));
    }

    @RequestMapping("many/{name}/{repetitions}")
    public String sayTheTimeManyParamsPath(Params params) {
        return IntStream.rangeClosed(1, params.getRepetitions())
                .mapToObj(i -> i + ". Hello, " + params.getName() + "! Now it is " + LocalDateTime.now())
                .collect(Collectors.joining("\n"));
    }

    static class Params {
        String name;
        int repetitions;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRepetitions() {
            return repetitions;
        }

        public void setRepetitions(int repetitions) {
            this.repetitions = repetitions;
        }
    }
}
