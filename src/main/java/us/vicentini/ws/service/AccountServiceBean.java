package us.vicentini.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.vicentini.ws.model.Account;
import us.vicentini.ws.repository.AccountRepository;

/**
 *
 * @author Shulander
 */
@Service
public class AccountServiceBean implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Override
    public Account findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        return account;
    }
    
}
