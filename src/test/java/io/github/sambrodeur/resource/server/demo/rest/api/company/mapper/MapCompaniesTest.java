package io.github.sambrodeur.resource.server.demo.rest.api.company.mapper;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MapCompaniesTest extends CompanyModelMapperTest {

  @InjectMocks
  private CompanyModelMapper companyModelMapper;

  private List<Company> companies;

  @BeforeEach
  void setUp() {
    companies = getCompanies();
  }

  @Test
  void mapCompaniesSucess() {
    List<CompanyModel> companiesModel = companyModelMapper.map(companies);

    assertNotNull(companiesModel);
    assertFalse(companiesModel.isEmpty());
  }
}
