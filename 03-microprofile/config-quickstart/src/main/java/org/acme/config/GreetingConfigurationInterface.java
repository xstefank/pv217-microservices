package org.acme.config;

import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@ConfigProperties(prefix = "greeting")
public interface GreetingConfigurationInterface {

    @ConfigProperty(name = "message")
    String message();

    // doesn't work
    @ConfigProperty(defaultValue = "!")
    String getSuffix();

    Optional<String> getName();
}
