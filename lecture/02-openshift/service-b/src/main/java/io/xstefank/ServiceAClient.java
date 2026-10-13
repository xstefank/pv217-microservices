package io.xstefank;

import jakarta.ws.rs.GET;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "service-a")
public interface ServiceAClient {

   @GET
   String whatever();
}
