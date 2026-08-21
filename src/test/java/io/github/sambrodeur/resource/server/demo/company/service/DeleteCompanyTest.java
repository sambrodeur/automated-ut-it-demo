package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.company.dao.CompanyDAO;
import io.github.sambrodeur.resource.server.demo.contact.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class DeleteCompanyTest extends CompanyServiceImplTest {

  @InjectMocks
  private CompanyServiceImpl companyService;

  @Mock
  private CompanyDAO companyDAO;

  @Mock
  private ContactService contactService;

  private Integer id;

  @BeforeEach
  void setUp() {
    id = 1;
  }

  @Test
  void deleteCompanySuccess() {
    assertDoesNotThrow(() -> companyService.deleteCompany(id));
  }
}
