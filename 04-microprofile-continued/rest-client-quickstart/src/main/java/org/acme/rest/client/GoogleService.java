package org.acme.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
@RegisterRestClient(configKey = "google-api")
public interface GoogleService {

    @GET
    @Path("/search")
    @Produces(MediaType.TEXT_PLAIN)
    String search(@QueryParam("q") String searchString);
}
