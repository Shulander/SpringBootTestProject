package us.vicentini.ws.util;

/**
 * Request Context class.
 *
 * @author Shulander
 */
public class RequestContext {

    private static final ThreadLocal<String> usernames;

    static {
        usernames = new ThreadLocal<>();
    }

    private RequestContext() {

    }

    public static String getUsername() {
        return usernames.get();
    }

    public static void setUsername(String username) {
        usernames.set(username);
    }

    public static void init() {
        usernames.set(null);
    }
}
