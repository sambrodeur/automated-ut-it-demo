package io.github.sambrodeur.resource.server.demo.rest.api.company.controller;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.service.CompanyService;
import io.github.sambrodeur.resource.server.demo.rest.api.company.mapper.CompanyModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCompanyByIdTest extends CompanyControllerTest {

  @InjectMocks
  private CompanyController companyController;

  @Mock
  private CompanyService companyService;

  @Mock
  private CompanyModelMapper companyModelMapper;

  private Company company;

  @BeforeEach
  void setUp() {
    company = getCompany();
  }

  @Test
  void getCompanyByIdSuccess() {
    when(companyService.getCompanyById(any())).thenReturn(company);
    when(companyModelMapper.map(any(Company.class))).thenReturn(getCompanyModel());

    assertNotNull(companyController.getCompanyById(1));
  }

  @Test
  void getCompanyByIdFail() {
    when(companyService.getCompanyById(any())).thenReturn(null);

    assertNotNull(companyController.getCompanyById(1));
  }
}
