package us.vicentini.ws.web.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
		g1.setText("Hola Mundo!");
		save(g2);
	}

	private static Greeting save(Greeting greeting) {
		greeting.setId(nextId);
		nextId++;
		greetingMap.put(greeting.getId(), greeting);

		return greeting;
	}

	@RequestMapping(value = "/api/greetings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
		Collection<Greeting> greetings = greetingMap.values();

		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
	}
}
