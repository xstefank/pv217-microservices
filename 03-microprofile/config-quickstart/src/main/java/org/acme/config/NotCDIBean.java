package org.acme.config;

import org.eclipse.microprofile.config.ConfigProvider;

// POJO - plain old java object on purpose
// no bean defining annotation like @ApplicationScoped or @RequestScoped
public class NotCDIBean {

    public String getConfigMessage() {
        return ConfigProvider.getConfig().getValue("greeting.message", String.class);
    }
}
