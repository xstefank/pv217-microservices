package io.xstefank.client;

import io.quarkus.oidc.token.propagation.common.AccessToken;
import io.xstefank.entity.Avenger;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/snap")
@AccessToken
@RegisterRestClient(configKey = "snap-service-client")
public interface SnapServiceClient {

    @POST
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    boolean shouldBeSnapped(Avenger avenger);
}