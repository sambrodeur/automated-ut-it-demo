package io.github.sambrodeur.resource.server.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties()
public class ResourceServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerDemoApplication.class, args);
	}
}