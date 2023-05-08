package com.mercans.assignment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.mercans.assignment.service.CsvProcessor;
import com.mercans.assignment.utils.AppUtils;

/**
 * 
 * @author Abdul Saboor 
 * Class providing application entry point
 * 
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.mercans.assignment.* ,com.mercans.assignment.interfaces.impl" })
public class BootstrapApplication {

	@Autowired AppUtils appUtils;

	public static void main(String[] args) {
		SpringApplication.run(BootstrapApplication.class, args);
	}
	
	@PostConstruct
	public void loadConfigs() {
		appUtils.loadConfigurations();
	}

}
