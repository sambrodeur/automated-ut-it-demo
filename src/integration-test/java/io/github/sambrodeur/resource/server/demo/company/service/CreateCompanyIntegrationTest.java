package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.dto.CRUDCompany;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CreateCompanyIntegrationTest extends CompanyServiceImplIntegrationTest {

  @Autowired
  private CompanyService companyService;

  private CRUDCompany crudCompany;

  @BeforeEach
  void setUp() {
    crudCompany = getCRUDCompany();
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void createCompanySuccess() {
    assertEquals(DEFAULT_COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    Company company = companyService.createCompany(crudCompany);

    assertEquals(DEFAULT_COMPANY_COUNT + 1, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertNotNull(company);
    validateCompany(company);
  }

  @Test
  void createCompanyWithData() {
    insertData();
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    Company company = companyService.createCompany(crudCompany);

    assertEquals(COMPANY_COUNT + 1, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertNotNull(company);
    validateCompany(company);
  }
}
