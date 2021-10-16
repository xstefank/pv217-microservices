package org.acme.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

@ConfigMapping(prefix = "pv217")
public interface PV217Config {

    @WithDefault("A217")
    String room();

    @WithDefault("Tue 10:00")
    String time();
}
