package us.vicentini.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.vicentini.ws.model.Account;

/**
 *
 * @author Shulander
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
