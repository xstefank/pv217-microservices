package org.acme.rest.client;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Path("/country-jaxrs")
public class CountriesResourceJAXRS {

    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target("https://restcountries.eu/rest");

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Country> name(@PathParam String name) {
        return target.path("/v2/name/" + name)
            .request()
            .get()
            .readEntity(new GenericType<Set<Country>>() {});
    }

    @GET
    @Path("/name-async/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Country> nameAsync(@PathParam String name) throws ExecutionException, InterruptedException {
        Future<Response> responseFuture = target.path("/v2").path("/name").path(name)
            .request()
            .async()
            .get();
        Response response = responseFuture.get();
        return response.readEntity(new GenericType<Set<Country>>() {});
    }

}
