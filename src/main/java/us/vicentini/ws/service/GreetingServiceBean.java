package us.vicentini.ws.service;

import java.util.Collection;
import static org.apache.catalina.security.SecurityUtil.remove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import us.vicentini.ws.model.Greeting;
import us.vicentini.ws.repository.GreetingRepository;

/**
 *
 * @author Shulander
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GreetingServiceBean implements GreetingService{

	@Autowired
	private GreetingRepository greetingRepository;
	
	@Override
	public Collection<Greeting> findAll() {
		return greetingRepository.findAll();
	}

	@Override
	public Greeting findOne(Long id) {
		return greetingRepository.findOne(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Greeting create(Greeting greeting) {
		if(greeting.getId() != null) {
			// Cannot create Greeting with specified ID value
			return null;
		}
		Greeting saveGreeting = greetingRepository.save(greeting);
	
		// Illustrate Transaction rollback
		if(saveGreeting.getId() == 4L) {
			throw new RuntimeException("Roll me back");
		}
		
		return saveGreeting;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Greeting update(Greeting greeting) {
		if(greeting.getId() == null) {
			// Cannot update Greeting with null ID value
			return null;
		}
		
		return greetingRepository.save(greeting);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Long id) {
		greetingRepository.delete(id);
	}
	
}
