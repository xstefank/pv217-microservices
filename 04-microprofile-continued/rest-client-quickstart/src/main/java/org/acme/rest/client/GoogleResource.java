package org.acme.rest.client;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/google")
public class GoogleResource {

    @Inject
    @RestClient
    GoogleService googleService;

    @GET
    public String searchGoogle(@QueryParam("search") String s) {
        return googleService.search(s);
    }
}
