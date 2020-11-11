package io.xstefank;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "another-rest")
@Path("/ping")
public interface AnotherRESTClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Avenger hello();
}
