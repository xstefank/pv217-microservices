package org.acme.client;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import java.util.Random;

@RegisterRestClient(configKey = "service-b")
@Path("/hello")
//@ClientHeaderParam(name = "CustomHeader", value = "staticHeader")
@ClientHeaderParam(name = "CustomHeader", value = "{computeHeader}")
public interface ServiceB {

    @GET
    String helloFromServiceB();

    @GET
    @Path("/header")
    String customHeader();

    default String computeHeader() {
        return "dynamic header " + new Random().nextDouble();
    }
}
