/**
 * 
 */
package de.arp.transformer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author arp
 *
 */
@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {

	@Autowired
	private Environment env;

	@Bean
	public Docket apiDocs() {
		return new Docket(DocumentationType.SWAGGER_2).
				select().
					paths(PathSelectors.regex("/transformer/api/.*")).
					build().
				apiInfo(metadata());
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder().
				title(env.getProperty("info.app.name")).
				description(env.getProperty("info.app.description")).
				version(env.getProperty("info.app.version")).
				build();
	}


}
