package ru.gb.springbootlesson3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.springbootlesson3.demo.SecondBean;

@SpringBootApplication
public class SpringBootLesson3Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootLesson3Application.class, args);

        context.getBean(SecondBean.class).postConstruct();
    }

}
