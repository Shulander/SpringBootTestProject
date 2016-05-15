package us.vicentini.ws.web.api;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import us.vicentini.ws.model.Role;
import us.vicentini.ws.repository.RoleRepository;

/**
 *
 * @author Shulander
 */
@RestController
public class RoleController extends BaseController {

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(
            value = "/api/roles",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Role>> getRoles(@RequestParam(
            value = "querytype",
            defaultValue = "annotation") String queryType) {

        Collection<Role> roles;

        if (queryType.equals("method")) {
            Date now = new Date();
            roles = roleRepository.findByEffectiveAtBeforeAndExpiresAtAfterOrExpiresAtNullOrderByOrdinal(now, now);
        } else {
            roles = roleRepository.findAllEfective(new Date());
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
