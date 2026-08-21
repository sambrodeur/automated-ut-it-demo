package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.company.dao.CompanyDAO;
import io.github.sambrodeur.resource.server.demo.company.dto.CRUDCompany;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CreateCompanyTest extends CompanyServiceImplTest {

  @InjectMocks
  private CompanyServiceImpl companyService;

  @Mock
  private CompanyDAO companyDAO;

  private CRUDCompany crudCompany;

  @BeforeEach
  void setUp() {
    crudCompany = getCRUDCompany();
  }

  @Test
  void createCompanySuccess() {
    assertNotNull(companyService.createCompany(crudCompany));
  }
}
