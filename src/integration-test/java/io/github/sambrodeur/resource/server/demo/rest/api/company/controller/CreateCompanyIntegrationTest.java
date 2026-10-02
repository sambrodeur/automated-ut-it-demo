package io.github.sambrodeur.resource.server.demo.rest.api.company.controller;

import io.github.sambrodeur.resource.server.demo.rest.api.company.CRUDCompanyModel;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyModel;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyTypeModel;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CreateCompanyIntegrationTest extends CompanyControllerIntegrationTest {

  private static final String URI = "/api/companies";

  private RestTestClient restTestClient;

  private CRUDCompanyModel crudCompanyModel;

  @BeforeEach
  void setUp(@Autowired WebApplicationContext context) {
    restTestClient = RestTestClient.bindToApplicationContext(context).build();

    insertData();
    crudCompanyModel = getCRUDCompanyModel();
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void createCompanySuccess() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    CompanyModel companyModel = restTestClient
      .post()
      .uri(URI)
      .body(crudCompanyModel)
      .exchange()
      .expectBody(CompanyModel.class)
      .returnResult()
      .getResponseBody();

    assertEquals(COMPANY_COUNT + 1, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    assertNotNull(companyModel);
    assertEquals(crudCompanyModel.name(), companyModel.name());
    assertEquals(crudCompanyModel.companyType(), companyModel.companyType());
    assertNotNull(companyModel.id());
    assertTrue(companyModel.contacts().isEmpty());
  }

  @Test
  void createCompanyFail() {
    crudCompanyModel = new CRUDCompanyModel(null, CompanyTypeModel.FOOD);

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    byte[] companyModel = restTestClient
      .post()
      .uri(URI)
      .body(crudCompanyModel)
      .exchange()
      .expectStatus().is5xxServerError()
      .expectBody()
      .returnResult()
      .getResponseBody();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    assertNotNull(companyModel);
  }
}
