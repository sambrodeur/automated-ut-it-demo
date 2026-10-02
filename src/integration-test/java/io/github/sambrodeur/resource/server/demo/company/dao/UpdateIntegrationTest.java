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
class UpdateIntegrationTest extends CompanyDAOImplIntegrationTest {

  @Autowired
  private CompanyDAO companyDAO;

  private Company company;

  @BeforeEach
  void setUp() {
    insertData();
    company = getCompany();
    company.setId(1000);
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void updateSuccess() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    companyDAO.update(company);

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertNotNull(company);
    validateCompany(company);
  }
}
