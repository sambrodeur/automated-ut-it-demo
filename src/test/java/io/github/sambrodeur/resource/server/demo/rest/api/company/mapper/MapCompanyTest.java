package io.github.sambrodeur.resource.server.demo.rest.api.company.mapper;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MapCompanyTest extends CompanyModelMapperTest {

  @InjectMocks
  private CompanyModelMapper companyModelMapper;

  private Company company;

  @BeforeEach
  void setUp() {
    company = getCompany();
  }

  @Test
  void mapCompanySuccess() {
    CompanyModel companyModel = companyModelMapper.map(company);

    assertNotNull(companyModel);
    assertEquals(company.getId(), companyModel.id());
    assertEquals(company.getName(), companyModel.name());
    assertEquals(company.getCompanyType().getKey(), companyModel.companyType().getKey());
    assertEquals(company.getContacts().size(), companyModel.contacts().size());
  }
}
