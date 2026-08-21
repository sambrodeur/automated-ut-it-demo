package io.github.sambrodeur.resource.server.demo.rest.api.company.mapper;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CRUDCompanyModel;
import org.instancio.Instancio;

import java.util.List;

abstract class CompanyModelMapperTest {

  Company getCompany() {
    return Instancio.of(Company.class).create();
  }

  CRUDCompanyModel getCRUDCompanyModel() {
    return Instancio.of(CRUDCompanyModel.class).create();
  }

  List<Company> getCompanies() {
    return List.of(Instancio.of(Company.class).create());
  }
}
