package us.vicentini.ws;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import us.vicentini.Application;

/**
 *
 * @author Shulander
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
public class AbstractTest {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
}
