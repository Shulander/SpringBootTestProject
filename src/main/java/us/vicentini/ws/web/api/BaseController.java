package us.vicentini.ws.web.api;

import java.util.Map;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import us.vicentini.ws.util.DefaultExceptionAttributes;
import us.vicentini.ws.util.IExceptionAttributes;

/**
 * Base Controller Class.
 * 
 * @author Shulander
 */
public class BaseController {

    /**
     * The Logger for this class hierarchy.
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception exception, HttpServletRequest request) {
        logger.error("> handleException");
        logger.error("- Exception: ", exception);

        IExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();

        Map<String, Object> responseBody = exceptionAttributes
                .getExceptionAttributes(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);

        logger.error("< handleException");
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Map<String, Object>> handleNoResultException(NoResultException exception, 
            HttpServletRequest request) {
        logger.error("> handleNoResultException");

        IExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();

        Map<String, Object> responseBody = exceptionAttributes
                .getExceptionAttributes(exception, request, HttpStatus.NOT_FOUND);

        logger.error("< handleNoResultException");
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}
