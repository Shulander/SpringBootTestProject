package us.vicentini.ws.util;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Shulander
 */
public interface IExceptionAttributes {

	Map<String, Object> getExceptionAttributes(Exception exception, HttpServletRequest httpRequest, HttpStatus httpStatus);
}
