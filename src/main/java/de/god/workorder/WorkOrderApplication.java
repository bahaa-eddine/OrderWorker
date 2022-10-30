package de.god.workorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author belbaghazaoui
 *
 */
@SpringBootApplication
public class WorkOrderApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WorkOrderApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WorkOrderApplication.class, args);
    }

}
