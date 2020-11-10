package io.xstefank.service;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class SnapGenerator {

    private static final Logger LOGGER = Logger.getLogger(SnapGenerator.class);

    private final Random random = new Random();
    private AtomicLong counter = new AtomicLong(0);

    @Retry(maxRetries = 2)
    @Fallback(fallbackMethod = "getDefaultSnap")
    public boolean shouldBeSnap(String avengerName) {
        final Long invocationNumber = counter.getAndIncrement();

        maybeFail(String.format("SnapGenerator#shouldBeSnap() invocation #%d failed", invocationNumber));

        LOGGER.infof("SnapGenerator#shouldBeSnap() invocation #%d returning successfully", invocationNumber);

        if (avengerName.equals("Thanos")) {
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
            throw new RuntimeException("Resource failure.");
        }
    }
}
