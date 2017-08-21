package com.example.packtpub.yummy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("many2/{name}/{repetitions}")
    public String sayTheTimeManyPathExplicit(@PathVariable String name, @PathVariable int repetitions) {
        return IntStream.rangeClosed(1, repetitions)
                .mapToObj(i -> i + ". Hello, " + name + "! Now it is " + LocalDateTime.now())
                .collect(Collectors.joining("\n"));
    }

//    @RequestMapping(value = "manyParams", method = RequestMethod.POST)
    @PostMapping(value = "manyParams")
    public String sayTheTimeManyPost(@RequestBody Params params) {
        return IntStream.rangeClosed(1, params.getRepetitions())
                .mapToObj(i -> i + ". Hello, " + params.getName() + "! Now it is " + LocalDateTime.now())
                .collect(Collectors.joining("\n"));
    }

    static class Params {
        String name;
        int repetitions;

        public Params() {}

        public Params(String name, int repetitions) {
            this.name = name;
            this.repetitions = repetitions;
        }

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        int getRepetitions() {
            return repetitions;
        }

        void setRepetitions(int repetitions) {
            this.repetitions = repetitions;
        }
    }
}
