package io.github.sambrodeur.resource.server.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI defaultOpenAPI() {
    return new OpenAPI()
      .components(new Components()
        .addParameters("X-API-VERSION", new Parameter()
          .name("X-API-VERSION")
          .in("header")
          .required(true)
          .description("API version")
          .schema(new StringSchema().example("1"))
        )
      )
      .info(new Info()
        .title("My API")
        .version("1.0.0")
        .description("Company API documentation")
        .contact(new Contact()
          .name("Dev Team")
          .email("dev@example.com"))
        .license(new License()
          .name("Apache 2.0")
          .url("https://www.apache.org/licenses/LICENSE-2.0")));
  }
}
