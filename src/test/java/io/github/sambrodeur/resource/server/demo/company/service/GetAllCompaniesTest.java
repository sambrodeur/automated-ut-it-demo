package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.dao.CompanyDAO;
import io.github.sambrodeur.resource.server.demo.contact.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllCompaniesTest extends CompanyServiceImplTest {

  @InjectMocks
  private CompanyServiceImpl companyService;

  @Mock
  private CompanyDAO companyDAO;

  @Mock
  private ContactService contactService;

  private List<Company> companies;

  @BeforeEach
  void setUp() {
    companies = List.of(getCompany());
  }

  @Test
  void getAllCompaniesSuccess() {
    when(companyDAO.findAll()).thenReturn(companies);

    assertNotNull(companyService.getAllCompanies());
  }
}
