package io.github.sambrodeur.resource.server.demo.company.dao;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.CompanyType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FindByCompanyTypeIntegrationTest extends CompanyDAOImplIntegrationTest {

  @Autowired
  private CompanyDAO companyDAO;

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
  void findByCompanyTypeSuccess() {
    List<Company> companies = companyDAO.findByCompanyType(companyType);

    assertNotNull(companies);
    assertFalse(companies.isEmpty());
    assertEquals(1, companies.size());
    companies.forEach(this::validateCompany);
  }

  @Test
  void findByCompanyTypeServicesSuccess() {
    companyType = CompanyType.SERVICES;

    List<Company> companies = companyDAO.findByCompanyType(companyType);

    assertNotNull(companies);
    assertFalse(companies.isEmpty());
    assertEquals(1, companies.size());
    companies.forEach(this::validateCompany);
  }

  @Test
  void findByCompanyTypeHardwareSuccess() {
    companyType = CompanyType.HARDWARE;

    List<Company> companies = companyDAO.findByCompanyType(companyType);

    assertNotNull(companies);
    assertFalse(companies.isEmpty());
    assertEquals(1, companies.size());
    companies.forEach(this::validateCompany);
  }

  @Test
  void findByCompanyTypeHealthSuccess() {
    companyType = CompanyType.HEALTH;

    List<Company> companies = companyDAO.findByCompanyType(companyType);

    assertNotNull(companies);
    assertFalse(companies.isEmpty());
    assertEquals(1, companies.size());
    companies.forEach(this::validateCompany);
  }
}
