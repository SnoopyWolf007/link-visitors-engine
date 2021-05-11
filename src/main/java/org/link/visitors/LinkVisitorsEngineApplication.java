package org.link.visitors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author link-kit
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LinkVisitorsEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkVisitorsEngineApplication.class, args);
    }

}
