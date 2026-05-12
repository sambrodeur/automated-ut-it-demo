package io.github.sambrodeur.resource.server.demo.contact;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class Contact implements Serializable {

  @Serial
  private static final long serialVersionUID = -8869112406887982167L;

  private Integer id;
  private ContactType contactType;
  private String contactValue;
  private Date creationDate;
  private Date modificationDate;

  public Contact() {
    id = null;
    contactType = null;
    contactValue = null;
    creationDate = null;
    modificationDate = null;
  }
}
