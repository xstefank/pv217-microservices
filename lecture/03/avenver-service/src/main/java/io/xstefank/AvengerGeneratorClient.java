package io.xstefank;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8082")
@Path("/avenger")
public interface AvengerGeneratorClient {

    @GET
    @Path("/generate")
    Avenger generateAvenger();
}
