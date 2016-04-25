package us.vicentini.ws.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import us.vicentini.ws.model.Greeting;

/**
 *
 * @author Shulander
 */
@Service
public class GreetingServiceBean implements GreetingService{

	private static Long nextId;
	private static final Map<Long, Greeting> greetingMap;

	static {
		nextId = 1L;
		greetingMap = new HashMap<>();

		Greeting g1 = new Greeting();
		g1.setText("Hello World!");
		save(g1);

		Greeting g2 = new Greeting();
		g2.setText("Hola Mundo!");
		save(g2);
	}

	private static Greeting save(Greeting greeting) {

		// If update
		if (greeting.getId() != null) {
			Greeting oldGreeting = greetingMap.get(greeting.getId());
			if (oldGreeting == null) {
				return null;
			}
			greetingMap.remove(greeting.getId());
		} else {
			// if create
			greeting.setId(nextId);
			nextId++;
		}
		greetingMap.put(greeting.getId(), greeting);

		return greeting;
	}

	private static boolean remove(Long id) {
		Greeting deletedGreeting = greetingMap.remove(id);

		return deletedGreeting != null;
	}
	
	@Override
	public Collection<Greeting> findAll() {
		return greetingMap.values();
	}

	@Override
	public Greeting findOne(Long id) {
		return greetingMap.get(id);
	}

	@Override
	public Greeting create(Greeting greeting) {
		return save(greeting);
	}

	@Override
	public Greeting update(Greeting greeting) {
		return save(greeting);
	}

	@Override
	public void delete(Long id) {
		remove(id);
	}
	
}
