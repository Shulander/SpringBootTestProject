package us.vicentini.ws.web.api;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import us.vicentini.ws.AbstractControllerTest;
import us.vicentini.ws.model.Greeting;
import us.vicentini.ws.service.GreetingService;

/**
 * Unit tests for the GreetingController using Spring MVC Mocks.
 *
 * <p>These tests utilize the Spring MVC Mock objects to simulate sending actual
 * HTTP requests to the Controller component. This test ensures that the
 * RequestMappings are configured correctly. Also, these tests ensure that the
 * request and response bodies are serialized as expected.</p>
 *
 * @author Matt Warman
 */
@Transactional
public class RoleControllerTest extends AbstractControllerTest {

    @Autowired
    private GreetingService greetingService;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        greetingService.evictCache();
    }

    @Test
    public void testGetRoles() throws Exception {

        String uri = "/api/roles";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

    }

}
