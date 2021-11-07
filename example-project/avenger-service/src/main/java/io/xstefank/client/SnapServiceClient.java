package io.xstefank.client;

import io.quarkus.oidc.token.propagation.AccessToken;
import io.xstefank.entity.Avenger;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
