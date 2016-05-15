package us.vicentini.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.vicentini.ws.model.Greeting;

/**
 * Greeting Repository class.
 * 
 * @author Shulander
 */
@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {

}
