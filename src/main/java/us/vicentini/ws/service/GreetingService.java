package us.vicentini.ws.service;

import java.util.Collection;
import us.vicentini.ws.model.Greeting;

/**
 *
 * @author Shulander
 */
public interface GreetingService {

	Collection<Greeting> findAll();

	Greeting findOne(Long id);

	Greeting create(Greeting greeting);

	Greeting update(Greeting greeting);

	void delete(Long id);

}
