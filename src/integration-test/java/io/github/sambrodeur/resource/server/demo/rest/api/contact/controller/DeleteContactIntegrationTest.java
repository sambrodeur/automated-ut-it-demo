package io.github.sambrodeur.resource.server.demo.rest.api.contact.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DeleteContactIntegrationTest extends ContactControllerIntegrationTest {

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
  void deleteContactSuccess() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    restTestClient
      .delete()
      .uri(uriBuilder -> uriBuilder.path(URI).build(companyId, id))
      .exchange()
      .expectStatus().isNoContent();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertEquals(CONTACT_COUNT - 1, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
  }
}
