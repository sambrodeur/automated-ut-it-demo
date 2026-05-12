package io.github.sambrodeur.resource.server.demo.config;

import io.github.sambrodeur.resource.server.demo.config.resolver.SwaggerAwareApiVersionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiVersionConfiguration implements WebMvcConfigurer {

  @Autowired
  private SwaggerAwareApiVersionResolver swaggerAwareApiVersionResolver;

  @Override
  public void configureApiVersioning(ApiVersionConfigurer configurer) {
    configurer
      .useVersionResolver(swaggerAwareApiVersionResolver)
      .setDefaultVersion("1");
  }
}
