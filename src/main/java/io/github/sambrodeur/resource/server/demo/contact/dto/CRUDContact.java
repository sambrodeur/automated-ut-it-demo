package io.github.sambrodeur.resource.server.demo.contact.dto;

import io.github.sambrodeur.resource.server.demo.contact.ContactType;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.ContactTypeModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class CRUDContact implements Serializable {

  @Serial
  private static final long serialVersionUID = -3674172773280458718L;

  private ContactType contactType;
  private String contactValue;

  public CRUDContact() {
    contactType = null;
    contactValue = null;
  }
}
