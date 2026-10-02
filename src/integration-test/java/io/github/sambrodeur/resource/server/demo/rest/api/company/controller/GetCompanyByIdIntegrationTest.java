package io.github.sambrodeur.resource.server.demo.rest.api.company.controller;

import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GetCompanyByIdIntegrationTest extends CompanyControllerIntegrationTest {

  private static final String URI = "/api/companies/{id}";

  private RestTestClient restTestClient;
  private Integer id;

  @BeforeEach
  void setUp(@Autowired WebApplicationContext context) {
    restTestClient = RestTestClient.bindToApplicationContext(context).build();

    id = 1000;
    insertData();
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void getCompanyByIdSuccess() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    CompanyModel companyModel = restTestClient
      .get()
      .uri(uriBuilder -> uriBuilder.path(URI).build(id))
      .exchange()
      .expectBody(CompanyModel.class)
      .returnResult()
      .getResponseBody();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    assertNotNull(companyModel);
    assertNotNull(companyModel.contacts());
    assertFalse(companyModel.contacts().isEmpty());
    assertEquals(2, companyModel.contacts().size());
    assertEquals(id, companyModel.id());
  }

  @Test
  void getCompanyByIdWithoutContactsSuccess() {
    id = 1001;

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    CompanyModel companyModel = restTestClient
      .get()
      .uri(uriBuilder -> uriBuilder.path(URI).build(id))
      .exchange()
      .expectBody(CompanyModel.class)
      .returnResult()
      .getResponseBody();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    assertNotNull(companyModel);
    assertNotNull(companyModel.contacts());
    assertTrue(companyModel.contacts().isEmpty());
    assertEquals(id, companyModel.id());
  }

  @Test
  void getCompanyByIdNotFoundSuccess() {
    id = 9;

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    byte[] companyModel = restTestClient
      .get()
      .uri(uriBuilder -> uriBuilder.path(URI).build(id))
      .exchange()
      .returnResult()
      .getRequestBodyContent();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    assertNotNull(companyModel);
  }
}
