package wiss.m295_lb._95_lb_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the 295_LB_Backend Spring Boot application.
 * 
 * This class serves as the entry point for the application and enables:
 * - Spring Boot auto-configuration
 * - Component scanning for the entire package structure
 * - Configuration property binding
 * 
 * The application will start on the default port 8080 and connect to the MySQL
 * database as configured in application.properties.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
