package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "serviceb")
@Path("/hello")
public interface ServiceBClient {

    @GET
    String helloFromB();

    @GET
    @Path("/counter")
    String counter();
}
