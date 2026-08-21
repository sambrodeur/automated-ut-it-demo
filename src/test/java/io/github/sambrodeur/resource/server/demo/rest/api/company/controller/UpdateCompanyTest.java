package io.github.sambrodeur.resource.server.demo.rest.api.company.controller;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.service.CompanyService;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CRUDCompanyModel;
import io.github.sambrodeur.resource.server.demo.rest.api.company.mapper.CompanyModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCompanyTest extends CompanyControllerTest {

  @InjectMocks
  private CompanyController companyController;

  @Mock
  private CompanyService companyService;

  @Mock
  private CompanyModelMapper companyModelMapper;

  private CRUDCompanyModel crudCompanyModel;
  private Company company;

  @BeforeEach
  void setUp() {
    crudCompanyModel = getCRUDCompanyModel();
    company = getCompany();

    when(companyModelMapper.map(crudCompanyModel)).thenReturn(getCrudCompany());

  }

  @Test
  void updateCompanySuccess() {
    when(companyService.updateCompany(eq(1), any())).thenReturn(company);

    assertNotNull(companyController.updateCompany(1, crudCompanyModel));
  }

  @Test
  void updateCompanyFail() {
    doThrow(new IllegalArgumentException("Company not found")).when(companyService).updateCompany(any(), any());
    assertNotNull(companyController.updateCompany(1, crudCompanyModel));
  }
}
