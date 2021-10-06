package io.xstefank;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Path("/ping")
public class PingResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
        return ClientBuilder.newClient()
            .target("https://www.google.com")
            .request()
            .get()
            .readEntity(String.class);
    }
}
