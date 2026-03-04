package wiss.m295_lb._95_lb_backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type Web config
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Allows requests to the API from the frontend.
     *
     * @param registry the registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}
