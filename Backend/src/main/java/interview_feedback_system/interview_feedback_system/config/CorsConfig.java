package interview_feedback_system.interview_feedback_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc  // Ensures Spring Boot applies CORS settings properly
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  
                        .allowedOrigins("http://localhost:8080")  //Allow requests from Vue.js frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Fix issue with `PUT`
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true)  // Allow cookies/auth headers (important for JWT)
                        .maxAge(3600); // Cache CORS preflight response for 1 hour
            }
        };
    }
}
