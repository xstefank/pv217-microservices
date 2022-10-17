package org.acme;

import org.acme.client.ServiceB;
import org.acme.client.TestBean;
import org.acme.entity.Avenger;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Path("/avengersadfdsaf")
@ApplicationScoped
public class AvengerResource2 {

    private static final Logger LOG = Logger.getLogger(AvengerResource2.class);
    private AtomicLong counter = new AtomicLong(0);

    @RestClient
    ServiceB serviceB;

    @Inject
    TestBean testBean;

    @GET
    public List<Avenger> getAll() {
        return Avenger.listAll();
    }

    @POST
    @Transactional
    public Avenger create(Avenger avenger) {
        avenger.persist();

        return avenger;
    }

    @GET
    @Path("/call")
    @Produces(MediaType.TEXT_PLAIN)
    @Retry(maxRetries = 5)
    @Fallback(fallbackMethod = "fallbackCall")
    public String call() {
        LOG.info("Invocation #" + counter.getAndIncrement());

        LOG.info(testBean.getHello("Martin"));
        return "Got hello: " + serviceB.customHeader();
    }

    public String fallbackCall() {
        return "Fallback value";
    }


}
