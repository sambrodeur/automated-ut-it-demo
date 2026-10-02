package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.CommonIntegrationTest;
import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.CompanyType;
import io.github.sambrodeur.resource.server.demo.company.dto.CRUDCompany;
import io.github.sambrodeur.resource.server.demo.contact.Contact;

import java.util.ArrayList;
import java.util.Date;

abstract class CompanyServiceImplIntegrationTest extends CommonIntegrationTest {

  protected static final int COMPANY_COUNT = 4;
  protected static final int CONTACT_COUNT = 2;

  CRUDCompany getCRUDCompany() {
    CRUDCompany crudCompany = new CRUDCompany();
    crudCompany.setName("Name1");
    crudCompany.setCompanyType(CompanyType.FOOD);

    return crudCompany;
  }

  Company getCompany() {
    Company company = new Company();
    company.setId(1);
    company.setName("Name1");
    company.setCompanyType(CompanyType.FOOD);
    company.setContacts(new ArrayList<Contact>());
    company.setCreationDate(new Date());
    company.setModificationDate(new Date());

    return company;
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
