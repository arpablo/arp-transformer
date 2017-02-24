/**
 * 
 */
package de.arp.transformer.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import de.arp.transformer.api.TransformerService;


/**
 * This class impelements the Web MVC controller for
 * the application
 * @author arp
 *
 */
@Controller
public class TransformerController {

	private static Logger logger = LoggerFactory.getLogger(TransformerController.class);
	
	@Autowired
	private TransformerService service;
	
	@PostConstruct
	public void init() {
		if (service != null) {
			logger.info("Service 'Transformer' is using implementation class {}",service.getClass().getName());
		}
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
