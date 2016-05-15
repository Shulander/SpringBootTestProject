package us.vicentini.ws.web.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import us.vicentini.ws.util.RequestContext;

/**
 * Request Context Initialization Filter.
 * 
 * @author Shulander
 */
@Component
public class RequestContextInitializationFilter extends GenericFilterBean {

    /**
     * The Logger for this class hierarchy.
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, 
            FilterChain chain) throws IOException, ServletException {
        logger.info("> doFilter");
//        RequestContext.init();
        
        chain.doFilter(req, resp);
        logger.info("< doFilter");
    }
    
}
