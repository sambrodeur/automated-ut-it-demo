package io.github.sambrodeur.resource.server.demo.company.dao;

import io.github.sambrodeur.resource.server.demo.company.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FindByIdIntegrationTest extends CompanyDAOImplIntegrationTest {

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
  void findByIdSuccess() {
    Company company = companyDAO.findById(id);

    assertNotNull(company);
    validateCompany(company);
  }
}
