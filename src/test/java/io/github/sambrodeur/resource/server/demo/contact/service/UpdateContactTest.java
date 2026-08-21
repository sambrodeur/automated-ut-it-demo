package io.github.sambrodeur.resource.server.demo.contact.service;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.ContactType;
import io.github.sambrodeur.resource.server.demo.contact.dao.ContactDAO;
import io.github.sambrodeur.resource.server.demo.contact.dto.CRUDContact;
import io.github.sambrodeur.resource.server.demo.contact.dto.ContactDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateContactTest extends ContactServiceImplTest {

  @InjectMocks
  private ContactServiceImpl contactService;

  @Mock
  private ContactDAO contactDAO;

  private Integer companyId;
  private Integer contactId;
  private Contact contact;
  private ContactDB contactDB;
  private CRUDContact crudContact;

  @BeforeEach
  void setUp() {
    companyId = 1;
    contactId = 1;

    contact = getContact();
    contactDB = getContactDB();
    contactDB.getContact().setId(1);
    crudContact = getCRUDContact();
  }

  @Test
  void updateContactSuccess() {
    when(contactDAO.findByCompanyId(any())).thenReturn(List.of(contactDB));
    when(contactDAO.update(any())).thenReturn(contact);

    assertNotNull(contactService.updateContact(companyId, contactId, crudContact));
  }

  @Test
  void updateContactFailNoContact() {
    when(contactDAO.findByCompanyId(any())).thenReturn(null);

    assertThrows(IllegalArgumentException.class, () -> contactService.updateContact(companyId, contactId, crudContact));
  }

  @Test
  void updateContactFailNoExistingId() {
    contactDB.getContact().setId(3);

    assertThrows(IllegalArgumentException.class, () -> contactService.updateContact(companyId, contactId, crudContact));
  }
}
