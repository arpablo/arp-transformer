/**
 * 
 */
package de.arp.transformer;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

/**
 * The spring boot application class for microservice Transformer
 * @author arp
 *
 */
@EnableCaching
@SpringBootApplication(scanBasePackages={"de.arp.transformer"})
public class TransformerApplication {
	
	private static Logger logger = LoggerFactory.getLogger(TransformerApplication.class);
	
	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(TransformerApplication.class);
		SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
		addDefaultProfile(app, source);
		Environment env = app.run(args).getEnvironment();
		logger.info("\n----------------------------------------------------------\n\t" + 
				"Application '{}' is running! \n\tAccess URLs:\n\t" + "Local: \t\t\thttp://127.0.0.1:{}\n\t" + 
				"External: \t\thttp://{}:{}\n\t" +
				"\n----------------------------------------------------------",
				env.getProperty("spring.application.name"), env.getProperty("server.port"), 
				InetAddress.getLocalHost().getHostAddress(), 
				env.getProperty("server.port")
				);
		
	}

	/**
	 * If no profile has been configured, set by default the "dev" profile.
	 */
	private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
		if (!source.containsProperty("spring.profiles.active") && !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {

			app.setAdditionalProfiles("dev");
		}
	}
		
}