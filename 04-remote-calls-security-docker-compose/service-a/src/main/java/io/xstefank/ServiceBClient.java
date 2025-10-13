package io.xstefank;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/hello")
@RegisterRestClient
public interface ServiceBClient  {

    @GET
    String hello(@HeaderParam("test-header") String headerValue);
}
