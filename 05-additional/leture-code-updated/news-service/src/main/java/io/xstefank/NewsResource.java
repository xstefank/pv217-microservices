package io.xstefank;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/news")
public class NewsResource {

    private static final Logger LOG = Logger.getLogger(NewsResource.class);

    @Inject
    @RestClient
    NewsVerificationServiceClient newsVerificationServiceClient;

    @POST
    @Path("/old")
    //@Produces(MediaType.APPLICATION_JSON) -- not needed because resteasy-jackson auto assigns application/json media types
    public News createNews(String news) {
        String trimmedNews = news.trim(); // required for httpie (it's appending \n)
        LOG.infof("Received news '%s'", trimmedNews);
        return newsVerificationServiceClient.verifyNews(trimmedNews);
    }

    //@Inject -- not needed anymore as quarkus can detect @Channel automatically
    @Channel("news")
    Emitter<String> newsEmitter;

    @POST
    public String createNewsReactive(String news) {
        String trimmedNews = news.trim(); // required for httpie (it's appending \n)
        LOG.infof("Received news '%s'", trimmedNews);

        newsEmitter.send(trimmedNews);

        return String.format("News '%s' will be verified later", trimmedNews);
    }

    @Channel("news")
    Multi<String> newsMulti; //changed to Multi from Publisher in the recording (specific type for quarkus)

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<String> stream() {
        return newsMulti;
    }

//    @Incoming("news")
//    public void consumeNews(String news) {
//        LOG.infof("Received '%s' news", news);
//    }
}
