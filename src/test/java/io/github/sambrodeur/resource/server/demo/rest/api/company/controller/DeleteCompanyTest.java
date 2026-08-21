package io.github.sambrodeur.resource.server.demo.rest.api.company.controller;

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
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class DeleteCompanyTest extends CompanyControllerTest {

  @InjectMocks
  private CompanyController companyController;

  @Mock
  private CompanyService companyService;

  @Mock
  private CompanyModelMapper companyModelMapper;

  private Integer id;

  @BeforeEach
  void setUp() {
    id = 1;
  }

  @Test
  void deleteCompanySuccess() {
    assertNotNull(companyController.deleteCompany(id));
  }

  @Test
  void deleteCompnayFail() {
    doThrow(new RuntimeException("Company not found")).when(companyService).deleteCompany(any());
    assertNotNull(companyController.deleteCompany(id));
  }
}
