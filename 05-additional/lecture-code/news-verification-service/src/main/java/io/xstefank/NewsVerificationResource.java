package io.xstefank;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;

@Path("/verify")
public class NewsVerificationResource {

    private static final Logger LOG = Logger.getLogger(NewsVerificationResource.class);

    private final Random random = new Random();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public News verifyNews(String news) {
        boolean verified = random.nextBoolean();
        LOG.infof("News '%s' %s verified", news, verified ? "was" : "wasn't");

        return new News(news, verified);
    }

    @Incoming("news")
    @Outgoing("verified-news")
    public News verifyNewsReactive(String news) {
        boolean verified = random.nextBoolean();
        LOG.infof("News '%s' %s verified", news, verified ? "was" : "wasn't");

        return new News(news, verified);
    }
}
