package io.github.sambrodeur.resource.server.demo.company.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DeleteByIdIntegrationTest extends CompanyDAOImplIntegrationTest {

  @Autowired
  private CompanyDAO companyDAO;

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
  void deleteByIdSuccess() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));

    companyDAO.deleteById(id);

    assertEquals(COMPANY_COUNT - 1, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
  }
}
