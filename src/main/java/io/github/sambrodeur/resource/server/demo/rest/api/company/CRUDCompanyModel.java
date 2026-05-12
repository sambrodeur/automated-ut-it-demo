package io.github.sambrodeur.resource.server.demo.rest.api.company;

import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

public record CRUDCompanyModel(@NotBlank String name,
                               @NotBlank CompanyTypeModel companyType) implements Serializable {

  @Serial
  private static final long serialVersionUID = -6116407519178661136L;
}
