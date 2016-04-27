package us.vicentini.ws.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
public class GreetingServiceBean implements GreetingService {

	@Autowired
	private GreetingRepository greetingRepository;

	@Override
	public Collection<Greeting> findAll() {
		return greetingRepository.findAll();
	}

	@Override
	@Cacheable(value = "greetings", key = "#id")
	public Greeting findOne(Long id) {
		return greetingRepository.findOne(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greetings", key = "#result.id")
	public Greeting create(Greeting greeting) {
		if (greeting.getId() != null) {
			// Cannot create Greeting with specified ID value
			return null;
		}
		Greeting saveGreeting = greetingRepository.save(greeting);

		return saveGreeting;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greetings", key = "#greeting.id")
	public Greeting update(Greeting greeting) {
		if (greeting.getId() == null) {
			// Cannot update Greeting with null ID value
			return null;
		}

		return greetingRepository.save(greeting);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CacheEvict(value = "greetings", key = "#id")
	public void delete(Long id) {
		greetingRepository.delete(id);
	}

	@Override
	@CacheEvict(value = "greetings", allEntries = true)
	public void evictCache() {

	}

}
