package io.github.sambrodeur.resource.server.demo.rest.api.contact;

import java.io.Serial;
import java.io.Serializable;

public record CRUDContactModel(ContactTypeModel contactType,
                               String contactValue) implements Serializable {

  @Serial
  private static final long serialVersionUID = 3978820581878142870L;
}
