package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.dto.CRUDCompany;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UpdateCompanyIntegrationTest extends CompanyServiceImplIntegrationTest {

  @Autowired
  private CompanyService companyService;

  private CRUDCompany crudCompany;
  private Integer id;

  @BeforeEach
  void setUp() {
    insertData();

    crudCompany = getCRUDCompany();
    id = 1000;
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void updateCompanySuccess() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    Company company = companyService.updateCompany(id, crudCompany);

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertNotNull(company);
    validateCompany(company);
  }

  @Test
  void updateCompanyWrongId() {
    id = 2000;

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    assertThrows(EmptyResultDataAccessException.class, () -> companyService.updateCompany(id, crudCompany));

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
  }
}
