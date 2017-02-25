/**
 * 
 */
package de.arp.transformer.config;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * This class holds the configuration for microservice Transformer
 * @author arp
 *
 */
@Configuration
@EnableBatchProcessing
public class TransformerConfig {
	
	private static Logger logger = LoggerFactory.getLogger(TransformerConfig.class);
	
	@Autowired 
	private JobBuilderFactory jobBuilderFactory;
	@Autowired 
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private JobRegistry jobRegistry;
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private JobExplorer jobExplorer;
	@Autowired
	private JobLauncher jobLauncher;
	
	@PostConstruct
	public void postCreate() {
		logger.info("Using JobBuilderFactory class {}", 
				jobBuilderFactory != null ? jobBuilderFactory.getClass().getName() : "null");
		logger.info("Using StepBuilderFactory class {}", 
				stepBuilderFactory != null ? stepBuilderFactory.getClass().getName() : "null");
	}
	
}