package io.xstefank.client;

import io.quarkus.oidc.token.propagation.common.AccessToken;
import io.xstefank.entity.Avenger;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/avenger")
@AccessToken
@RegisterRestClient(configKey = "avenger-generator-client")
public interface AvengerGeneratorClient {

    @GET
    @Path("/generate")
    Avenger generateAvenger();
}