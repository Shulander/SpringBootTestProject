package us.vicentini.ws.web.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import us.vicentini.ws.model.Greeting;

/**
 *
 * @author Shulander
 */
@RestController
public class GreettingController {

	private static Integer nextId;
	private static final Map<Integer, Greeting> greetingMap;

	static {
		nextId = 1;
		greetingMap = new HashMap<Integer, Greeting>();

		Greeting g1 = new Greeting();
		g1.setText("Hello World!");
		save(g1);

		Greeting g2 = new Greeting();
		g2.setText("Hola Mundo!");
		save(g2);
	}

	private static Greeting save(Greeting greeting) {
		
		if(greeting.getId() != null) {
			Greeting oldGreeting = greetingMap.get(greeting.getId());
			if(oldGreeting == null) {
				return null;
			}
			greetingMap.remove(greeting.getId());
		} else {
			greeting.setId(nextId);
			nextId++;
		}
		greetingMap.put(greeting.getId(), greeting);

		return greeting;
	}
	
	private static boolean delete(Integer id) {
		Greeting deletedGreeting = greetingMap.remove(id);
		
		return deletedGreeting!=null;
	}

	@RequestMapping(value = "/api/greetings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
		Collection<Greeting> greetings = greetingMap.values();

		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Integer id) {

		Greeting greeting = greetingMap.get(id);

		if (greeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
		Greeting saveGreeting = save(greeting);

		return new ResponseEntity<Greeting>(saveGreeting, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {
		Greeting updatedGreeting = save(greeting);
		
		if(updatedGreeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Integer id, @RequestBody Greeting greeting) {
		if(!delete(id)) {
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
	}
}
