package io.xstefank;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/news")
public class NewsResource {

    private static final Logger LOG = Logger.getLogger(NewsResource.class);

    @Inject
    @RestClient
    NewsVerificationServiceClient newsVerificationServiceClient;

    @POST
    //@Produces(MediaType.APPLICATION_JSON) -- not needed because resteasy-jackson auto assigns application/json media types
    public News createNews(String news) {
        String trimmedNews = news.trim(); // required for httpie (it's appending \n)
        LOG.infof("Received news '%s'", trimmedNews);
        return newsVerificationServiceClient.verifyNews(trimmedNews);
    }
}
