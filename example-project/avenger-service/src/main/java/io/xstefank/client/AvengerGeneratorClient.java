package io.xstefank.client;

import io.xstefank.entity.Avenger;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/avenger")
@RegisterRestClient(configKey = "avenger-generator-client")
public interface AvengerGeneratorClient {

    @GET
    @Path("/generate")
    Avenger generateAvenger();
}
