package org.acme.rest.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestQuery;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MultivaluedMap;
import java.util.Map;
import java.util.Set;

@Path("/extensions")
@RegisterRestClient(configKey = "extensions-api")
public interface ExtensionsService {

    @GET
    Set<Extension> getById(@QueryParam("id") String id);

    @GET
    Uni<Set<Extension>> getByIdAsUni(@QueryParam("id") String id);

    @GET
    Set<Extension> getByName(@RestQuery String name);

    @GET
    Set<Extension> getByFilter(@RestQuery Map<String, String> filter);

    @GET
    Set<Extension> getByFilters(@RestQuery MultivaluedMap<String, String> filters);

    @GET
    @Path("/stream/{stream}")
    Set<Extension> getByStream(@PathParam("stream") String stream, @QueryParam("id") String id);

}