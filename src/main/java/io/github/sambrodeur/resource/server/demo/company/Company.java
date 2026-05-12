package io.github.sambrodeur.resource.server.demo.company;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Company implements Serializable {

  @Serial
  private static final long serialVersionUID = -4457022292033032202L;

  private Integer id;
  private String name;
  private CompanyType companyType;
  private List<Contact> contacts;
  private Date creationDate;
  private Date modificationDate;


  public Company() {
    id = null;
    name = null;
    companyType = null;
    contacts = new ArrayList<>();
    creationDate = null;
    modificationDate = null;
  }
}
