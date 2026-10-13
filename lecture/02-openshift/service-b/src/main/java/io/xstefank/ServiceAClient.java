package io.xstefank;

import jakarta.ws.rs.GET;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8080/hello")
public interface ServiceAClient {

   @GET
   String whatever();
}
