package org.acme.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestQuery;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MultivaluedMap;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.Map;
import java.util.Set;
import java.util.Optional;

@Path("/extensions")
@RegisterRestClient(configKey = "extensions-api")
public interface ExtensionsService {

    @GET
    Set<Extension> getById(@QueryParam("id") String id);

    @GET
    Set<Extension> getByName(@RestQuery String name);

    @GET
    Set<Extension> getByOptionalName(@RestQuery Optional<String> name);

    @GET
    Set<Extension> getByFilter(@RestQuery Map<String, String> filter);

    @GET
    Set<Extension> getByFilters(@RestQuery MultivaluedMap<String, String> filters);

    @GET
    RestResponse<Set<Extension>> getByIdResponseProperties(@RestQuery String id);

}