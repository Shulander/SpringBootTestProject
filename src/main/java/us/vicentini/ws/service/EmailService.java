package us.vicentini.ws.service;

import java.util.concurrent.Future;

import us.vicentini.ws.model.Greeting;

/**
 * Email Service interface.
 * @author Shulander
 */
public interface EmailService {

    /**
     * Sends an e-mail synchronous.
     * 
     * @param greeting Greeting object
     * @return the success send result
     */
    Boolean send(Greeting greeting);

    /**
     * Sends an e-mail asynchronous.
     * 
     * @param greeting Greeting object
     */
    void sendAsync(Greeting greeting);

    /**
     * Sends an e-mail and returns a Future Result.
     * 
     * @param greeting Greeting object
     * @return Future boolean indicating the success or not
     */
    Future<Boolean> sendAsyncWithResult(Greeting greeting);
}
