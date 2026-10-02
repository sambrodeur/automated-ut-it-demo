package io.github.sambrodeur.resource.server.demo.rest.api.contact.controller;

import io.github.sambrodeur.resource.server.demo.rest.api.common.ContactModel;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.CRUDContactModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UpdateContactIntegrationTest extends ContactControllerIntegrationTest {

  private static final String URI = "/api/companies/{companyId}/contacts/{id}";

  private RestTestClient restTestClient;

  private Integer companyId;
  private Integer id;
  private CRUDContactModel crudContactModel;

  @BeforeEach
  void setUp(@Autowired WebApplicationContext context) {
    restTestClient = RestTestClient.bindToApplicationContext(context).build();

    insertData();
    companyId = 2000;
    id = 1000;
    crudContactModel = getCRUDContactModel();
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void updateContactSuccess() {
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    ContactModel contactModel = restTestClient
      .put()
      .uri(uriBuilder -> uriBuilder.path(URI).build(companyId, id))
      .body(crudContactModel)
      .exchange()
      .expectBody(ContactModel.class)
      .returnResult()
      .getResponseBody();

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    assertNotNull(contactModel);
    assertEquals(crudContactModel.contactValue(), contactModel.contactValue());
    assertEquals(crudContactModel.contactType(), contactModel.contactType());
    assertNotNull(contactModel.id());
  }
}
