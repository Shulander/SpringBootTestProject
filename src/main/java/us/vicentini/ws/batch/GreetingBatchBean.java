package us.vicentini.ws.batch;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import us.vicentini.ws.model.Greeting;
import us.vicentini.ws.service.GreetingService;

/**
 * activate: --spring.profiles.active=batch
 *
 * @author Shulander
 */
@Profile("batch")
@Component
public class GreetingBatchBean {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private GreetingService greetingService;

    /**
     * Batch scheduler: CronJob.
     *
     * <p>
     * this method uses the properties configured in the application-batch.properties file</p>
     */
    @Scheduled(cron = "${batch.greeting.cron}")
    public void cronJob() {
        logger.info("> cronJob");
        
        Collection<Greeting> greetings = greetingService.findAll();
        logger.info("There are {} greetings in the data store.", greetings.size());
        
        logger.info("< cronJob");
    }

    /**
     * Batch scheduler: FixedRate with initial delay.
     *
     * <p>
     * this method uses the properties configured in the application-batch.properties file</p>
     */
    @Scheduled(
        initialDelayString = "${batch.greeting.initialdelay}",
        fixedRateString = "${batch.greeting.fixdrate}")
    public void fixedRateJobWithInitialDelay() {
        logger.info("> fixedRateJobWithInitialDelay");

        // Add scheduled logic here
        // Simulate job processing time
        long pause = 5000;
        long start = System.currentTimeMillis();
        do {
            if (start + pause < System.currentTimeMillis()) {
                break;
            }
        } while (true);
        
        logger.info("Processing time was {} seconds.", pause / 1000);
        logger.info("< fixedRateJobWithInitialDelay");
    }

    /**
     * Batch scheduler: FixedDelay with initial delay.
     *
     * <p>
     * this method uses the properties configured in the application-batch.properties file</p>
     */
    @Scheduled(
        initialDelayString = "${batch.greeting.initialdelay}",
        fixedDelayString = "${batch.greeting.fixeddelay}")
    public void fixedDelayJobWithInitialDelay() {
        logger.info("> fixedRateJobWithInitialDelay");

        // Add scheduled logic here
        // Simulate job processing time
        long pause = 5000;
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(pause);
        } catch (InterruptedException ex) {
            logger.error("Interrupted thread error: " + ex.getMessage(), ex);
        }
        
        logger.info("Processing time was {} seconds.", pause / 1000);
        logger.info("< fixedRateJobWithInitialDelay");
    }
}
