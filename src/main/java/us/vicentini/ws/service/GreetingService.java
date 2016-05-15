package us.vicentini.ws.service;

import java.util.Collection;

import us.vicentini.ws.model.Greeting;

/**
 * Greeting Service interface.
 * 
 * @author Shulander
 */
public interface GreetingService {

    /**
     * Retrieves all Greetings.
     * 
     * @return Greeting collection
     */
    Collection<Greeting> findAll();

    /**
     * Finds a Greeting identified by it's ID.
     * 
     * @param id Greetings ID
     * @return Greeting object or null if not found
     */
    Greeting findOne(Long id);

    /**
     * Create a Greeting object.
     *
     * <p>this object must not have it's id set.</p>
     * 
     * @param greeting new Greeting object
     * @return returns the persisted object
     */
    Greeting create(Greeting greeting);

    /**
     * Update a Greeting object.
     *
     * <p>this object must have it's id set.</p>
     * 
     * @param greeting Greeting object
     * @return returns the persisted object
     */
    Greeting update(Greeting greeting);

    /**
     * Deletes the Greeting identified by it's ID.
     * @param id Greetings ID
     */
    void delete(Long id);

    /**
     * Cleans the Greeting cache.
     */
    void evictCache();
}
