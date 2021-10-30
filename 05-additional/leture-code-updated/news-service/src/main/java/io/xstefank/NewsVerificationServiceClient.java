package io.xstefank;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@RegisterRestClient(baseUri = "http://localhost:8081")
@Path("/verify")
public interface NewsVerificationServiceClient {

    @POST
    News verifyNews(String news);
}
