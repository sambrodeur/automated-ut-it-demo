package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.dao.CompanyDAO;
import io.github.sambrodeur.resource.server.demo.company.dto.CRUDCompany;
import io.github.sambrodeur.resource.server.demo.contact.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCompanyTest extends CompanyServiceImplTest {

  @InjectMocks
  private CompanyServiceImpl companyService;

  @Mock
  private CompanyDAO companyDAO;

  @Mock
  private ContactService contactService;

  private Integer id;
  private CRUDCompany crudCompany;
  private Company company;

  @BeforeEach
  void setUp() {
    id = 1;
    crudCompany = getCRUDCompany();
    company = getCompany();
  }

  @Test
  void updateCompanySuccess() {
    when(companyDAO.findById(any())).thenReturn(company);

    assertNotNull(companyService.updateCompany(id, crudCompany));
  }

  @Test
  void updateCompanyNullException() {
    when(companyDAO.findById(any())).thenReturn(null);

    assertThrows(IllegalArgumentException.class, () -> companyService.updateCompany(id, crudCompany));
  }
}
