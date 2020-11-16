package io.xstefank;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "http://localhost:8081")
@Path("/verify")
public interface NewsVerificationsServiceClient {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    News verifyNews(String news);
}
