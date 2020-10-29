package org.acme.config;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/greeting")
public class GreetingResource {

    @ConfigProperty(name = "greeting.message")
    String message;

    @ConfigProperty(name = "greeting.suffix", defaultValue="!")
    String suffix;

    @ConfigProperty(name = "greeting.name")
    Optional<String> name;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message + " " + name.orElse("world") + suffix;
    }

    /*programmatic access*/

    @GET
    @Path("/programmatic")
    public void programmaticAccess() {
        String databaseName = ConfigProvider.getConfig().getValue("greeting.message", String.class);
        System.out.println(databaseName);
        Optional<String> maybeDatabaseName = ConfigProvider.getConfig().getOptionalValue("greeting.message", String.class);
        System.out.println(maybeDatabaseName.orElse("default"));
    }

    /*config properties*/

    @Inject
    GreetingConfiguration greetingConfiguration;

    @GET
    @Path("/config-properties")
    public void configProperties() {
        System.out.println(greetingConfiguration.getMessage() + " " + greetingConfiguration.getName().orElse("world") + greetingConfiguration.getSuffix());
    }

    @Inject
    GreetingConfigurationInterface greetingConfigurationInterface;

    @GET
    @Path("/config-properties-interface")
    public void configPropertiesInterface() {
        System.out.println(greetingConfigurationInterface.message() + " " + greetingConfigurationInterface.getName().orElse("world") + greetingConfigurationInterface.getSuffix());
    }

}
