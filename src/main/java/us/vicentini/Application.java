package us.vicentini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring Boot main application class. Serves as both the runtime application
 * entry point and the central Java configuration class.
 *
 */
@SpringBootApplication
@EnableTransactionManagement // looks for methods anotated with @Transactional
@EnableCaching               // looks for methods anotated with @Cache***
@EnableScheduling            // looks for methods anotated with @Scheduled
@EnableAsync                 // looks for methods anotated with @AssincronousTaskMethod
public class Application {

    /**
     * Entry point for the application.
     * 
     * @param args Command line arguments.
     * @throws Exception Thrown when an unexpected Exception is thrown from the
     *         application.
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Create a CacheManager implementation class to be used by Spring where
     * <code>@Cacheable</code> annotations are applied.
     * 
     * @return A CacheManager instance.
     */
    @Bean
    public CacheManager cacheManager() {
        // ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("greetings");
        GuavaCacheManager cacheManager = new GuavaCacheManager("greetings");

        return cacheManager;
    }
}
