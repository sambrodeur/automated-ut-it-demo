package io.github.sambrodeur.resource.server.demo.config.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.accept.ApiVersionResolver;

import java.util.List;

@Component
public class SwaggerAwareApiVersionResolver implements ApiVersionResolver {

  private static final List<String> BYPASS_PATHS = List.of(
    "/swagger-ui",
    "/v3/api-docs",
    "/swagger-resources",
    "/webjars",
    "/error",
    "/h2-console"
  );

  @Override
  public String resolveVersion(HttpServletRequest request) {
    String path = request.getRequestURI();

    // return default version for swagger paths so they're never rejected
    boolean isBypassPath = BYPASS_PATHS.stream().anyMatch(path::startsWith);
    if (isBypassPath) {
      return "1";
    }

    // otherwise read from header normally
    return request.getHeader("X-API-VERSION");
  }
}
