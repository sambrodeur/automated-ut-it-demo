package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.company.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GetCompanyByIdIntegrationTest extends CompanyServiceImplIntegrationTest {

  @Autowired
  private CompanyService companyService;

  private Integer id;

  @BeforeEach
  void setUp() {
    insertData();
    id = 1000;
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void getCompanyByIdSuccess() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    Company company = companyService.getCompanyById(id);

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    validateCompany(company);
  }
}
