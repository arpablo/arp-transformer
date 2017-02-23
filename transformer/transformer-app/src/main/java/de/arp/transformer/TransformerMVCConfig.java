/**
 * 
 */
package de.arp.transformer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This class implements the MVC configuration for the application
 * @author arp
 *
 */
@Configuration
public class TransformerMVCConfig extends WebMvcConfigurerAdapter {

	/**
	 * Do not try to determine the Content type from the path extension
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
	}


}