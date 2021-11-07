package io.xstefank.client;

import io.quarkus.oidc.token.propagation.AccessToken;
import io.xstefank.entity.Avenger;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/avenger")
@AccessToken
@RegisterRestClient(configKey = "avenger-generator-client")
public interface AvengerGeneratorClient {

    @GET
    @Path("/generate")
    Avenger generateAvenger();
}
