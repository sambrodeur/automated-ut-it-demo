package io.github.sambrodeur.resource.server.demo.contact.dto;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class ContactDB implements Serializable {

  @Serial
  private static final long serialVersionUID = 8373517064174095629L;

  private Integer companyId;
  private Contact contact;

  public ContactDB() {
    companyId = null;
    contact = null;
  }
}
