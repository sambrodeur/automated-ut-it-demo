package io.github.sambrodeur.resource.server.demo.rest.api.company.controller;

import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyModel;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyTypeModel;
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
class GetCompaniesByTypeIntegrationTest extends CompanyControllerIntegrationTest {

  private static final String URI = "/api/companies/type/{companyTypeModel}";

  private RestTestClient restTestClient;
  private CompanyTypeModel companyTypeModel;

  @BeforeEach
  void setUp(@Autowired WebApplicationContext context) {
    restTestClient = RestTestClient.bindToApplicationContext(context).build();

    companyTypeModel = CompanyTypeModel.FOOD;
    insertData();
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void getCompaniesByTypeFoodSuccess() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    List<CompanyModel> companyModels = restTestClient
      .get()
      .uri(uriBuilder -> uriBuilder.path(URI).build(companyTypeModel))
      .exchange()
      .expectBody(new ParameterizedTypeReference<List<CompanyModel>>() {})
      .returnResult()
      .getResponseBody();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    assertNotNull(companyModels);
    assertEquals(1, companyModels.size());
  }

  @Test
  void getCompaniesByTypeServicesSuccess() {
    companyTypeModel = CompanyTypeModel.SERVICES;

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    List<CompanyModel> companyModels = restTestClient
      .get()
      .uri(uriBuilder -> uriBuilder.path(URI).build(companyTypeModel))
      .exchange()
      .expectBody(new ParameterizedTypeReference<List<CompanyModel>>() {})
      .returnResult()
      .getResponseBody();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    assertNotNull(companyModels);
    assertEquals(1, companyModels.size());
  }

  @Test
  void getCompaniesByTypeHardwareSuccess() {
    companyTypeModel = CompanyTypeModel.HARDWARE;

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    List<CompanyModel> companyModels = restTestClient
      .get()
      .uri(uriBuilder -> uriBuilder.path(URI).build(companyTypeModel))
      .exchange()
      .expectBody(new ParameterizedTypeReference<List<CompanyModel>>() {})
      .returnResult()
      .getResponseBody();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    assertNotNull(companyModels);
    assertEquals(1, companyModels.size());
  }

  @Test
  void getCompaniesByTypeHealthSuccess() {
    companyTypeModel = CompanyTypeModel.HEALTH;

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    List<CompanyModel> companyModels = restTestClient
      .get()
      .uri(uriBuilder -> uriBuilder.path(URI).build(companyTypeModel))
      .exchange()
      .expectBody(new ParameterizedTypeReference<List<CompanyModel>>() {})
      .returnResult()
      .getResponseBody();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    assertNotNull(companyModels);
    assertEquals(1, companyModels.size());
  }
}
