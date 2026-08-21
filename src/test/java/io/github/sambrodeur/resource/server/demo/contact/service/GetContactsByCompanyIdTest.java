package io.github.sambrodeur.resource.server.demo.contact.service;

import io.github.sambrodeur.resource.server.demo.contact.dao.ContactDAO;
import io.github.sambrodeur.resource.server.demo.contact.dto.ContactDB;
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
class GetContactsByCompanyIdTest extends ContactServiceImplTest {

  @InjectMocks
  private ContactServiceImpl contactService;

  @Mock
  private ContactDAO contactDAO;

  private Integer companyId;
  private ContactDB contactDB;

  @BeforeEach
  void setUp() {
    companyId = 7;
    contactDB = getContactDB();
  }

  @Test
  void getContactsByCompanyIdSuccess() {
    when(contactDAO.findByCompanyId(companyId)).thenReturn(List.of(contactDB));

    assertNotNull(contactService.getContactsByCompanyId(companyId));
  }
}
