package io.github.sambrodeur.resource.server.demo.rest.api.company.controller;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.dto.CRUDCompany;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CRUDCompanyModel;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyModel;
import org.instancio.Instancio;

abstract class CompanyControllerTest {

  CRUDCompanyModel getCrudCompanyModel() {
    return Instancio.of(CRUDCompanyModel.class).create();
  }

  Company getCompany() {
    return Instancio.of(Company.class).create();
  }

  CRUDCompany getCrudCompany() {
    return Instancio.of(CRUDCompany.class).create();
  }

  CompanyModel getCompanyModel() {
    return Instancio.of(CompanyModel.class).create();
  }

  CRUDCompanyModel getCRUDCompanyModel() {
    return Instancio.of(CRUDCompanyModel.class).create();
  }
}
