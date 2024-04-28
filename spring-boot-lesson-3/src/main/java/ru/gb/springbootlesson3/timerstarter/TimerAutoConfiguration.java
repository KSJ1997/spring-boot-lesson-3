package ru.gb.springbootlesson3.timerstarter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimerAutoConfiguration {

    @Bean
    public TimerAspect timerAspect() {
        return new TimerAspect();
    }
}