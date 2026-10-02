package io.github.sambrodeur.resource.server.demo.rest.api.company.controller;

import io.github.sambrodeur.resource.server.demo.rest.api.company.CRUDCompanyModel;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UpdateCompanyIntegrationTest extends CompanyControllerIntegrationTest {

  private static final String URI = "/api/companies/{id}";

  private RestTestClient restTestClient;
  private Integer id;
  private CRUDCompanyModel crudCompanyModel;

  @BeforeEach
  void setUp(@Autowired WebApplicationContext context) {
    restTestClient = RestTestClient.bindToApplicationContext(context).build();

    id = 1000;
    crudCompanyModel = getCRUDCompanyModel();

    insertData();
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void updateCompanySuccess() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    CompanyModel companyModel = restTestClient
      .put()
      .uri(uriBuilder -> uriBuilder.path(URI).build(id))
      .body(crudCompanyModel)
      .exchange()
      .expectBody(CompanyModel.class)
      .returnResult()
      .getResponseBody();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    assertNotNull(companyModel);
    assertEquals(crudCompanyModel.name(), companyModel.name());
    assertEquals(crudCompanyModel.companyType(), companyModel.companyType());
    assertNotNull(companyModel.id());
  }
}
