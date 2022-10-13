package org.acme.rest.client;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Set;

@Path("/extension")
public class ExtensionsResource {

    @RestClient
    ExtensionsService extensionsService;


    @GET
    @Path("/id/{id}")
    public Set<Extension> id(String id) {
        return extensionsService.getById(id);
    }
}