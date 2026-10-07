package org.acme.rest.client;

import io.quarkus.logging.Log;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestQuery;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.Date;
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

    @GET
    @Path("/properties")
    public RestResponse<Set<Extension>> responseProperties(@RestQuery String id) {
        RestResponse<Set<Extension>> clientResponse = extensionsService.getByIdResponseProperties(id);
        String contentType = clientResponse.getHeaderString("Content-Type");
        int status = clientResponse.getStatus();
        String setCookie = clientResponse.getHeaderString("Set-Cookie");
        Date lastModified = clientResponse.getLastModified();

        Log.infof("content-Type: %s status: %s Last-Modified: %s Set-Cookie: %s", contentType, status, lastModified,
            setCookie);

        return RestResponse.fromResponse(clientResponse);
    }
}