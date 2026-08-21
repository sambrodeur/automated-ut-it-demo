package io.github.sambrodeur.resource.server.demo.company.dao;

import io.github.sambrodeur.resource.server.demo.company.Company;
import org.instancio.Instancio;

abstract class CompanyDAOImplTest {

  Company getCompany() {
    return Instancio.of(Company.class).create();
  }
}
