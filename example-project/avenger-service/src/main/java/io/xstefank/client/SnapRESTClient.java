package io.xstefank.client;

import io.xstefank.entity.Avenger;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "snap-service")
public interface SnapRESTClient {

    @POST
    @Path("/snap/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    boolean shouldBeSnapped(Avenger avenger);

    @GET
    @Path("/health")
    void callHealth();
}
