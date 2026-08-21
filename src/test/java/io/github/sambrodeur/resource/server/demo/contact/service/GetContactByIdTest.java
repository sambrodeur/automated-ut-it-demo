package io.github.sambrodeur.resource.server.demo.contact.service;

import io.github.sambrodeur.resource.server.demo.contact.dao.ContactDAO;
import io.github.sambrodeur.resource.server.demo.contact.dto.ContactDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetContactByIdTest extends ContactServiceImplTest {

  @InjectMocks
  private ContactServiceImpl contactService;

  @Mock
  private ContactDAO contactDAO;

  private Integer companyId;
  private Integer contactId;
  private ContactDB contactDB;

  @BeforeEach
  void setUp() {
    companyId = 1;
    contactId = 1;
    contactDB = getContactDB();
  }

  @Test
  void getContactByIdSuccess() {
    when(contactDAO.findById(contactId)).thenReturn(contactDB);

    assertNotNull(contactService.getContactById(companyId, contactId));
  }

  @Test
  void getContactByIdFailureDontMatch() {
    companyId = 2;

    when(contactDAO.findById(contactId)).thenReturn(contactDB);

    assertThrows(IllegalArgumentException.class, () -> contactService.getContactById(companyId, contactId));
  }

  @Test
  void getContactByIdFailure() {
    when(contactDAO.findById(3)).thenReturn(null);

    assertThrows(IllegalArgumentException.class, () -> contactService.getContactById(7, 3));
  }
}
