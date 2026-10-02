package io.github.sambrodeur.resource.server.demo.company.dao;

import io.github.sambrodeur.resource.server.demo.CommonIntegrationTest;
import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.CompanyType;
import io.github.sambrodeur.resource.server.demo.contact.Contact;

import java.util.ArrayList;
import java.util.Date;

abstract class CompanyDAOImplIntegrationTest extends CommonIntegrationTest {

  protected static final int COMPANY_COUNT = 4;

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
}
