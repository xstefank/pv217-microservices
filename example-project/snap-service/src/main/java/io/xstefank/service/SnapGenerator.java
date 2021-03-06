package io.xstefank.service;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class SnapGenerator {

    private static final Logger LOGGER = Logger.getLogger(SnapGenerator.class);

    private final Random random = new Random();
    private AtomicLong counter = new AtomicLong(0);

    @Inject
    @Metric(name = "snap.failures.counter")
    Counter snapFailuresCounter;

    @Inject
    @Metric(name = "snap.thanos.counter")
    Counter snapThanosCounter;

    @Retry(maxRetries = 2)
    @Fallback(fallbackMethod = "getDefaultSnap")
    public boolean shouldBeSnap(String avengerName) {
        final Long invocationNumber = counter.getAndIncrement();

        maybeFail(String.format("SnapGenerator#shouldBeSnap() invocation #%d failed", invocationNumber));

        LOGGER.infof("SnapGenerator#shouldBeSnap() invocation #%d returning successfully", invocationNumber);

        if (avengerName.equals("Thanos")) {
            snapThanosCounter.inc();
            return true;
        }

        return random.nextBoolean();
    }

    private boolean getDefaultSnap(String avengerName) {
        LOGGER.info("Falling back to SnapGenerator#getDefaultSnap()");
        // rather snap then don't snap :)
        return true;
    }

    private void maybeFail(String failureLogMessage) {
        // use different random then the one for snaps
        if (new Random().nextBoolean()) {
            LOGGER.error(failureLogMessage);
            snapFailuresCounter.inc();
            throw new RuntimeException("Resource failure.");
        }
    }
}
