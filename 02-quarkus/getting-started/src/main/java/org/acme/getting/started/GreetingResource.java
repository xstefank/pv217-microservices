package org.acme.getting.started;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("/call")
    public void getHelloFromService2() {
        Client client = ClientBuilder.newClient();

        try {
            Response response = client.target("http://localhost:8081/hello")
                .request()
                .get();

            System.out.printf("Status: %d%nHeaders: %s%nBody: %s%n",
                response.getStatus(), response.getHeaders(), response.readEntity(String.class));
        } finally {
            client.close();
        }
    }
}
