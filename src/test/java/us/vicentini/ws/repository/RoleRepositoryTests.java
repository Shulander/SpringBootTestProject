package us.vicentini.ws.repository;

import java.util.Collection;
import org.joda.time.DateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import us.vicentini.ws.AbstractTest;
import us.vicentini.ws.model.Account;
import us.vicentini.ws.model.Role;

/**
 * Unit tests for the Rolerepository interface.
 *
 * @author Matt Warman
 *
 */
@Transactional
public class RoleRepositoryTests extends AbstractTest {

    @Autowired
    private RoleRepository repository;

    @Before
    @Override
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testFindAllEfective() {

        Collection<Role> list = repository.findAllEfective(new DateTime());

        Assert.assertNotNull("failure - expected entity not null", list);
        Assert.assertEquals("failure - expected list size", 3, list.size());

    }


}
