package io.github.sambrodeur.resource.server.demo.company.dao;

import io.github.sambrodeur.resource.server.demo.company.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SaveIntegrationTest extends CompanyDAOImplIntegrationTest {

  @Autowired
  private CompanyDAO companyDAO;

  private Company company;

  @BeforeEach
  void setUp() {
    company = getCompany();
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void saveSuccess() {
    assertEquals(DEFAULT_COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    Company companySaved = companyDAO.save(company);

    assertEquals(DEFAULT_COMPANY_COUNT + 1, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertNotNull(companySaved);
    validateCompany(companySaved);
  }

  @Test
  void saveWithDataSuccess() {
    insertData();

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    Company companySaved = companyDAO.save(company);

    assertEquals(COMPANY_COUNT + 1, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertNotNull(companySaved);
    validateCompany(companySaved);
  }
}