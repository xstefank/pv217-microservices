package io.xstefank;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class FailingService {

    private static final Logger LOGGER = Logger.getLogger(FailingService.class);

    @Retry(maxRetries = 1)
    @Fallback(fallbackMethod = "getDefaultHello")
    public String getHello() {
        maybeFail("FAIL");
        return "hello";
    }

    private String getDefaultHello() {
        return "Default hello from fallback";
    }

    private void maybeFail(String failureLogMessage) {
        if (new Random().nextBoolean()) {
            LOGGER.error(failureLogMessage);
            throw new RuntimeException("Resource failure.");
        }
    }
}
