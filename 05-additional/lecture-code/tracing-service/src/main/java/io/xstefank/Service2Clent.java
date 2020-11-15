package io.xstefank;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "http://localhost:8081")
@Path("/ping")
public interface Service2Clent {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String hello();
}
