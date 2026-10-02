package io.github.sambrodeur.resource.server.demo.rest.api.company.controller;

import io.github.sambrodeur.resource.server.demo.CommonIntegrationTest;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CRUDCompanyModel;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyTypeModel;

abstract class CompanyControllerIntegrationTest extends CommonIntegrationTest {

  protected static final int COMPANY_COUNT = 4;
  protected static final int CONTACT_COUNT = 2;

  CRUDCompanyModel getCRUDCompanyModel() {
    return new CRUDCompanyModel("Company5", CompanyTypeModel.FOOD);
  }

  void insertData() {
    insertCompanies();
    insertContacts();
  }

  private void insertCompanies() {
    jdbcClient.sql("""
                   INSERT INTO company (id, name, company_type, creation_date, modification_date) VALUES
                   (1000, 'Company1', 1, now(), now()),
                   (1001, 'Company2', 2, now(), now()),
                   (1002, 'Company3', 3, now(), now()),
                   (1003, 'Company4', 4, now(), now())
                   """).update();
  }

  private void insertContacts() {
    jdbcClient.sql("""
                   INSERT INTO contact (id, company_id, contact_type, contact_value, creation_date, modification_date) VALUES
                   (1000, 1000, 1, 'first1.last1@example.com', now(), now()),
                   (1001, 1000, 2, '123-456-7890', now(), now())
                   """).update();
  }
}
