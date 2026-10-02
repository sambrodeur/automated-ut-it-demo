package io.github.sambrodeur.resource.server.demo.rest.api.contact.controller;

import io.github.sambrodeur.resource.server.demo.CommonIntegrationTest;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.CRUDContactModel;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.ContactTypeModel;

abstract class ContactControllerIntegrationTest extends CommonIntegrationTest {

  protected static final int COMPANY_COUNT = 2;
  protected static final int CONTACT_COUNT = 1;

  CRUDContactModel getCRUDContactModel() {
    return new CRUDContactModel(ContactTypeModel.PHONE, "123-456-7890");
  }

  void insertData() {
    insertCompanies();
    insertContacts();
  }

  private void insertCompanies() {
    jdbcClient.sql("""
                   INSERT INTO company (id, name, company_type, creation_date, modification_date) VALUES
                   (1000, 'Company1', 1, now(), now()),
                   (2000, 'Company2', 2, now(), now())
                   """).update();
  }

  private void insertContacts() {
    jdbcClient.sql("""
                   INSERT INTO contact (id, company_id, contact_type, contact_value, creation_date, modification_date) VALUES
                   (1000, 2000, 2, '123-456-7890', now(), now())
                   """).update();
  }
}
