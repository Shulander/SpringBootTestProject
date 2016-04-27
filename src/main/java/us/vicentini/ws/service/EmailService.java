package us.vicentini.ws.service;

import java.util.concurrent.Future;
import us.vicentini.ws.model.Greeting;

/**
 *
 * @author Shulander
 */
public interface EmailService {

	Boolean send(Greeting greeting);

	void sendAsync(Greeting greeting);

	Future<Boolean> sendAsyncWithResult(Greeting greeting);
}
