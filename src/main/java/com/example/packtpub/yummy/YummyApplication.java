package com.example.packtpub.yummy;

import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class YummyApplication {

	public static void main(String[] args) {
            ConfigurableApplicationContext context = SpringApplication.run(YummyApplication.class, args);
            for (String name : context.getBeanDefinitionNames()) {
                System.out.println("Bean: " + name);
            }
	}
}

@Component
class DatePrinter {
    @Autowired
    private TimeFactory timeFactory;
    
    public String printDate() {
        return "Now, it is " + timeFactory.now();
    }
    
    @PostConstruct
    public void post() {
        System.out.println("Output: " + printDate());
    }
}

class TimeFactory {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}

@Configuration
class MyConfiguration {
    @Bean
    public TimeFactory timeFactory() {
        return new TimeFactory();
    }
    
    @PostConstruct
    public void post() {
        // To illustrate that Spring uses the same instance
        System.out.println("\n\nInstance: " + timeFactory());
        System.out.println("Instance: " + timeFactory() +"\n\n");
    }
}