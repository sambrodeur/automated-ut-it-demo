package io.github.sambrodeur.resource.server.demo.company.dto;

import io.github.sambrodeur.resource.server.demo.company.CompanyType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class CRUDCompany implements Serializable {

  @Serial
  private static final long serialVersionUID = -4882232851699061513L;

  private String name;
  private CompanyType companyType;

  public CRUDCompany() {
    name = null;
    companyType = null;
  }
}
