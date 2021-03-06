package org.acme.config;

import io.quarkus.arc.config.ConfigPrefix;
import org.acme.config.custom.MicroProfileCustomValue;
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

    @Inject
    GreetingConfigurationPublicFields greetingConfigurationPublicFields;

    @GET
    @Path("/config-properties-public-fields")
    public void configPropertiesPublicFields() {
        System.out.println(greetingConfigurationPublicFields.message + " " + greetingConfigurationPublicFields.name.orElse("world") + greetingConfigurationPublicFields.suffix);
        System.out.println(greetingConfigurationPublicFields.content.prizeAmount + " " + greetingConfigurationPublicFields.content.recipients);
    }

    /*different prefixes*/

    @Inject
    GreetingConfiguration greetingConfigurationPrefixGreeting;

    @Inject
    @ConfigPrefix("other")
    GreetingConfiguration greetingConfigurationPrefixOther;

    @GET
    @Path("different-prefixes")
    public void differentPrefixes() {
        System.out.println(greetingConfigurationPrefixGreeting.getMessage());
        System.out.println(greetingConfigurationPrefixOther.getMessage());
    }

    /*custom converter*/

    @ConfigProperty(name = "custom.value")
    MicroProfileCustomValue customValue;

    @GET
    @Path("/custom-converter")
    public void customConverter() {
        System.out.println(customValue);
    }
}
