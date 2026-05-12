package io.github.sambrodeur.resource.server.demo.rest.api.common;

import io.github.sambrodeur.resource.server.demo.contact.ContactType;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.ContactTypeModel;

import java.io.Serial;
import java.io.Serializable;

public record ContactModel(Integer id, ContactTypeModel contactType, String contactValue) implements Serializable {

  @Serial
  private static final long serialVersionUID = 6400878855617183579L;

}
