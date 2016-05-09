package us.vicentini.ws.service;

import java.util.Collection;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GreetingRepository greetingRepository;
	
	@Autowired
	private CounterService counterService;

	@Override
	public Collection<Greeting> findAll() {
		counterService.increment("method.invoked.greetingServiceBean.findAll");
		return greetingRepository.findAll();
	}

	@Override
	@Cacheable(value = "greetings", key = "#id")
	public Greeting findOne(Long id) {
		counterService.increment("method.invoked.greetingServiceBean.findOne");
		return greetingRepository.findOne(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greetings", key = "#result.id")
	public Greeting create(Greeting greeting) {
		counterService.increment("method.invoked.greetingServiceBean.create");
		logger.info("> create");
		if (greeting.getId() != null) {
			// Cannot create Greeting with specified ID value
			logger.error("Attempted to create a Greeting, but id attribute was not null.");
			throw new EntityExistsException("The id attribute must be null to persist a new entity.");
		}
		Greeting saveGreeting = greetingRepository.save(greeting);

		logger.info("< create");
		return saveGreeting;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greetings", key = "#greeting.id")
	public Greeting update(Greeting greeting) {
		counterService.increment("method.invoked.greetingServiceBean.update");
		logger.info("> update id:{}", greeting.getId());
		if (greeting.getId() == null) {
			// Cannot update Greeting with null ID value
			logger.error("Attempted to update a Greeting with null ID value.");
			throw new NoResultException("Requested entity not found.");
		}

		// Ensure the entity object to be updated exists in the repository to
		// prevent the default behavior of save() which will persist a new
		// entity if the entity matching the id does not exist
		Greeting greetingToUpdate = findOne(greeting.getId());
		if (greetingToUpdate == null) {
			// Cannot update Greeting that hasn't been persisted
			logger.error("Attempted to update a Greeting, but the entity does not exist.");
			throw new NoResultException("Requested entity not found.");
		}

		logger.info("< update id:{}", greeting.getId());
		return greetingRepository.save(greeting);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CacheEvict(value = "greetings", key = "#id")
	public void delete(Long id) {
		counterService.increment("method.invoked.greetingServiceBean.delete");
		greetingRepository.delete(id);
	}

	@Override
	@CacheEvict(value = "greetings", allEntries = true)
	public void evictCache() {

	}

}
