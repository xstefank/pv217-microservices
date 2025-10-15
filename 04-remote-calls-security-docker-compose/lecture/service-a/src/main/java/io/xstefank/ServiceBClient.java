package io.xstefank;

import io.quarkus.oidc.token.propagation.common.AccessToken;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "service-b")
@AccessToken
@Path("/hello")
public interface ServiceBClient {

    @GET
    String hello(@HeaderParam("test-header") String testHeader);
}
