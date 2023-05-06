package com.mercans.assignment.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "integration")
public class ApplicationConfiguration {

	private String filename;
	private String path;

}
