package io.xstefank;

import jakarta.ws.rs.GET;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:8080/avenger")
public interface AvengerClient {

    @GET
    List<Avenger> getAvengers();
}
