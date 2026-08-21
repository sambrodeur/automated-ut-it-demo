package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.dto.CRUDCompany;
import org.instancio.Instancio;

abstract class CompanyServiceImplTest {

  CRUDCompany getCRUDCompany() {
    return Instancio.of(CRUDCompany.class).create();
  }

  Company getCompany() {
    return Instancio.of(Company.class).create();
  }
}
