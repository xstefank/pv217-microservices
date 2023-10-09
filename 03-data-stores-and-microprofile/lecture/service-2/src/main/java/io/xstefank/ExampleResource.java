package io.xstefank;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@Path("/")
public class ExampleResource {

    @ConfigProperty(name = "my.super.config")
    Optional<String> myConfig;

    @ConfigProperty(name = "person.from.config")
    Person person;

    @GET
    @Path("/config")
    @Counted
    @Timed
    public void config() {
        System.out.println("person = " + person);
        
        System.out.println("myConfig = " + myConfig.orElse("default-2"));
        System.out.println(ConfigProvider.getConfig().getValue("my.super.config", String.class));
//        System.out.println(ConfigProvider.getConfig().getPropertyNames());
    }


}
