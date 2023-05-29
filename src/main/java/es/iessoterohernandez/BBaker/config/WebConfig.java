package es.iessoterohernandez.BBaker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/profile")
                .setViewName("forward:/index.html");
                registry.addViewController("/profile")
                .setViewName("forward:/index.html");
    }
}