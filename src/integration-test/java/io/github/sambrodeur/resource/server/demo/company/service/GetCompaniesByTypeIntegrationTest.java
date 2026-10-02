package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.CompanyType;
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
class GetCompaniesByTypeIntegrationTest extends CompanyServiceImplIntegrationTest {

  @Autowired
  private CompanyService companyService;

  private CompanyType companyType;

  @BeforeEach
  void setUp() {
    insertData();

    companyType = CompanyType.FOOD;
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void getCompaniesByTypeFood() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    List<Company> companies = companyService.getCompaniesByType(companyType);

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertNotNull(companies);
    assertFalse(companies.isEmpty());
    assertEquals(1, companies.size());
    companies.forEach(this::validateCompany);
  }

  @Test
  void getCompaniesByTypeServices() {
    companyType = CompanyType.SERVICES;

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    List<Company> companies = companyService.getCompaniesByType(companyType);

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertNotNull(companies);
    assertFalse(companies.isEmpty());
    assertEquals(1, companies.size());
    companies.forEach(this::validateCompany);
  }

  @Test
  void getCompaniesByTypeHardware() {
    companyType = CompanyType.HARDWARE;

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    List<Company> companies = companyService.getCompaniesByType(companyType);

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertNotNull(companies);
    assertFalse(companies.isEmpty());
    assertEquals(1, companies.size());
    companies.forEach(this::validateCompany);
  }

  @Test
  void getCompaniesByTypeHealth() {
    companyType = CompanyType.HEALTH;

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    List<Company> companies = companyService.getCompaniesByType(companyType);

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertNotNull(companies);
    assertFalse(companies.isEmpty());
    assertEquals(1, companies.size());
    companies.forEach(this::validateCompany);
  }
}
