package org.acme.config;

import io.quarkus.arc.config.ConfigProperties;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@ConfigProperties(prefix = "greeting")
public class GreetingConfigurationPublicFields {

    @Size(min = 3)
    public String message;
    public String suffix = "!";
    public Optional<String> name;
    public ContentConfig content;

    public static class ContentConfig {
        public Integer prizeAmount;
        public List<String> recipients;
    }
}
