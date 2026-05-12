package io.github.sambrodeur.resource.server.demo.rest.api.company;

import io.github.sambrodeur.resource.server.demo.rest.api.common.ContactModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record CompanyModel(Integer id,
                           String name,
                           CompanyTypeModel companyType,
                           List<ContactModel> contacts) implements Serializable {

  @Serial
  private static final long serialVersionUID = -34668382061076599L;
}