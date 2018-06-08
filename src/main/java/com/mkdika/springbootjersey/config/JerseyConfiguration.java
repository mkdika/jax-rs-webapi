package com.mkdika.springbootjersey.config;

import com.mkdika.springbootjersey.person.PersonController;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
@Configuration
@ApplicationPath("api")
public class JerseyConfiguration extends ResourceConfig {

    @PostConstruct
    public void setUp() {
        register(PersonController.class);       
    }

}
