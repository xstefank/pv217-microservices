package org.acme.rest.client;

import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.net.URI;
import java.util.Set;

@Path("/extension")
public class ExtensionsResource {

    @RestClient
    ExtensionsService extensionsService;

//    public ExtensionsResource() {
//        extensionsService = QuarkusRestClientBuilder.newBuilder()
//            .baseUri(URI.create("https://stage.code.quarkus.io/api"))
//            .build(ExtensionsService.class);
//    }

    @GET
    @Path("/id/{id}")
    public Set<Extension> id(String id) {
        return extensionsService.getById(id);
    }

    @GET
    @Path("/id-uni/{id}")
    public Uni<Set<Extension>> idUni(String id) {
        return extensionsService.getByIdAsUni(id);
    }
}