package io.xstefank.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "token-issuer")
public interface TokenIssuerRESTClient {

    @GET
    @Path("/health")
    void callHealth();
}
