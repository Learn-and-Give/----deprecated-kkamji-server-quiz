package com.kkamgi.quiz;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
    @PropertySource("classpath:application.properties"),
    @PropertySource("file:/Users/kim-seunggyu/Desktop/kkamji/application-security.properties"),
})
public class SpringConfig {
}
