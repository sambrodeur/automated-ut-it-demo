package io.github.sambrodeur.resource.server.demo.rest.api.company.controller;

import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyModel;
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
class GetAllCompaniesIntegrationTest extends CompanyControllerIntegrationTest {

  private static final String URI = "/api/companies";

  private RestTestClient restTestClient;

  @BeforeEach
  void setUp(@Autowired WebApplicationContext context) {
    restTestClient = RestTestClient.bindToApplicationContext(context).build();

    insertData();
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void getCompanyByIdSuccess() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    List<CompanyModel> companyModels = restTestClient
      .get()
      .uri(URI)
      .exchange()
      .expectBody(new ParameterizedTypeReference<List<CompanyModel>>() {})
      .returnResult()
      .getResponseBody();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    assertNotNull(companyModels);
    assertEquals(COMPANY_COUNT, companyModels.size());
    validateNbContacts(CONTACT_COUNT, companyModels);
  }
}
