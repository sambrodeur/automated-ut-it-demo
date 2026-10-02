package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.company.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GetAllCompaniesIntegrationTest extends CompanyServiceImplIntegrationTest {

  @Autowired
  private CompanyService companyService;

  @BeforeEach
  void setUp() {
    insertData();
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void getAllCompaniesSuccess() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    List<Company> companies = companyService.getAllCompanies();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertNotNull(companies);
    assertFalse(companies.isEmpty());
    assertEquals(COMPANY_COUNT, companies.size());
    companies.forEach(this::validateCompany);
  }
}
