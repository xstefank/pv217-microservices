package org.acme.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestQuery;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Map;
import java.util.Set;

@Path("/extensions")
@RegisterRestClient(configKey = "extensions-api")
public interface ExtensionsService {

    @GET
    Set<Extension> getById(@QueryParam("id") String id);

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