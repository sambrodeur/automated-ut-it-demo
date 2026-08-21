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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllCompaniesTest extends CompanyControllerTest {

  @InjectMocks
  private CompanyController companyController;

  @Mock
  private CompanyService companyService;

  @Mock
  private CompanyModelMapper companyModelMapper;

  @BeforeEach
  void setUp() {
    Company company = getCompany();

    when(companyService.getAllCompanies()).thenReturn(List.of(company));
    when(companyModelMapper.map(anyList())).thenReturn(List.of(getCompanyModel()));
  }

  @Test
  void getAllCompaniesSuccess() {
    assertNotNull(companyController.getAllCompanies());
  }
}
