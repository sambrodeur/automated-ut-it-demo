package io.github.sambrodeur.resource.server.demo.rest.api.company.mapper;

import io.github.sambrodeur.resource.server.demo.company.dto.CRUDCompany;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CRUDCompanyModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MapCrudCompanyModelTest extends CompanyModelMapperTest {

  @InjectMocks
  private CompanyModelMapper companyModelMapper;

  private CRUDCompanyModel crudCompanyModel;

  @BeforeEach
  void setUp() {
    crudCompanyModel = getCRUDCompanyModel();
  }

  @Test
  void mapCRUDCompanyModelSuccess() {
    CRUDCompany crudCompany = companyModelMapper.map(crudCompanyModel);

    assertNotNull(crudCompany);
    assertEquals(crudCompanyModel.name(), crudCompany.getName());
    assertEquals(crudCompanyModel.companyType().getKey(), crudCompany.getCompanyType().getKey());
  }
}
