package us.vicentini.ws.security;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import us.vicentini.ws.model.Account;
import us.vicentini.ws.model.Role;
import us.vicentini.ws.service.AccountService;

/**
 *
 * @author Shulander
 */
@Service
public class AccountUserDetailService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username);

        if (account == null) {
            return null;
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Role role : account.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

        User userDetails = new User(account.getUsername(), account.getPassword(), 
                account.isEnabled(), !account.isExpired(), !account.isCredentialsexpired(), 
                !account.isLocked(), grantedAuthorities);

        return userDetails;
    }

}
