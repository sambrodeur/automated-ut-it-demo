package io.github.sambrodeur.resource.server.demo.rest.api.contact.controller;

import io.github.sambrodeur.resource.server.demo.rest.api.common.ContactModel;
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
class GetContactByIdIntegrationTest extends ContactControllerIntegrationTest {

  private static final String URI = "/api/companies/{companyId}/contacts/{id}";

  private RestTestClient restTestClient;

  private Integer companyId;
  private Integer id;

  @BeforeEach
  void setUp(@Autowired WebApplicationContext context) {
    restTestClient = RestTestClient.bindToApplicationContext(context).build();

    insertData();
    companyId = 2000;
    id = 1000;
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void getContactByIdSuccess() {
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    ContactModel contactModel = restTestClient
      .get()
      .uri(uriBuilder -> uriBuilder.path(URI).build(companyId, id))
      .exchange()
      .expectBody(ContactModel.class)
      .returnResult()
      .getResponseBody();

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    assertNotNull(contactModel);
    assertEquals(id, contactModel.id());
  }

  @Test
  void getContactByIdNotFoundSuccess() {
    id = 9;

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    byte[] contactModel = restTestClient
      .get()
      .uri(uriBuilder -> uriBuilder.path(URI).build(companyId, id))
      .exchange()
      .returnResult()
      .getRequestBodyContent();

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    assertNotNull(contactModel);
  }
}
