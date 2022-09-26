package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Client client = ClientBuilder.newClient();
        try {
            Response response = client.target("https://www.google.com")
                .request()
                .get();

            return response.readEntity(String.class);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}