package us.vicentini.ws.service;

import us.vicentini.ws.model.Account;

/**
 *
 * @author Shulander
 */
public interface AccountService {
    
    Account findByUsername(String username);
}
