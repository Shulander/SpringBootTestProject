package us.vicentini.ws.util;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

/**
 * Exception Attributes Interface.
 * @author Shulander
 */
public interface IExceptionAttributes {

    /**
     * Returns an exception attributes Map.
     * 
     * @param exception Exception 
     * @param httpRequest http request
     * @param httpStatus http status
     * @return attributes map
     */
    Map<String, Object> getExceptionAttributes(Exception exception
            , HttpServletRequest httpRequest, HttpStatus httpStatus);
}
