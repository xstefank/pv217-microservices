package io.xstefank;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ping")
@RegisterRestClient
public interface ServiceBClient {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String ping();

}
