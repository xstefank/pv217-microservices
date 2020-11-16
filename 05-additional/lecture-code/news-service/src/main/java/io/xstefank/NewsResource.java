package io.xstefank;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

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
    NewsVerificationsServiceClient newsVerificationsServiceClient;

    @POST
    @Path("/old")
    @Produces(MediaType.APPLICATION_JSON)
    public News createNews(String news) {
        String trimmedNews = news.trim(); // required for httpie (it's appending \n)
        LOG.infof("Recieved news '%s'", trimmedNews);
        return newsVerificationsServiceClient.verifyNews(trimmedNews);
    }

    @Inject
    @Channel("news")
    Emitter<String> newsEmitter;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String createNewsReactive(String news) {
        String trimmedNews = news.trim(); // required for httpie (it's appending \n)
        LOG.infof("Recieved news '%s'", trimmedNews);

        newsEmitter.send(trimmedNews);

        return String.format("News '%s' will be verified later", trimmedNews);
    }

    @Inject
    @Channel("verified-news")
    Publisher<String> newsPublisher;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    public Publisher<String> stream() {
        return newsPublisher;
    }


//    @Incoming("news")
//    public void consumeNews(String news) {
//        LOG.infof("Received '%s' news", news);
//    }
}
