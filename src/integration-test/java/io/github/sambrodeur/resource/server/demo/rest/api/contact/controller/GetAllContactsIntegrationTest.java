package io.github.sambrodeur.resource.server.demo.rest.api.contact.controller;

import io.github.sambrodeur.resource.server.demo.rest.api.common.ContactModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GetAllContactsIntegrationTest extends ContactControllerIntegrationTest {

  private static final String URI = "/api/companies/{companyId}/contacts";

  private RestTestClient restTestClient;

  private Integer companyId;

  @BeforeEach
  void setUp(@Autowired WebApplicationContext context) {
    restTestClient = RestTestClient.bindToApplicationContext(context).build();

    insertData();
    companyId = 2000;
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void getContactByIdSuccess() {
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    List<ContactModel> contactModels = restTestClient
      .get()
      .uri(uriBuilder -> uriBuilder.path(URI).build(companyId))
      .exchange()
      .expectBody(new ParameterizedTypeReference<List<ContactModel>>(){})
      .returnResult()
      .getResponseBody();

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    assertNotNull(contactModels);
    assertEquals(CONTACT_COUNT, contactModels.size());
  }
}
