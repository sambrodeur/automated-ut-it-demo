package io.github.sambrodeur.resource.server.demo.contact.service;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateContactTest extends ContactServiceImplTest {

  @InjectMocks
  private ContactServiceImpl contactService;

  @Mock
  private ContactDAO contactDAO;

  private CRUDContact crudContact;
  private Contact contact;

  @BeforeEach
  void setUp() {
    crudContact = getCRUDContact();
    contact = getContact();

    contact.setContactType(crudContact.getContactType());
    contact.setContactValue(crudContact.getContactValue());
  }

  @Test
  void createContactSuccess() {
    when(contactDAO.findByCompanyId(7)).thenReturn(List.of());
    when(contactDAO.save(any(Contact.class), eq(7))).thenReturn(contact);

    assertNotNull(contactService.createContact(crudContact, 7));
  }

  @Test
  void createContactFail() {
    ContactDB contactDB = getContactDB();
    contactDB.setContact(contact);

    when(contactDAO.findByCompanyId(7)).thenReturn(List.of(contactDB));

    assertThrows(IllegalArgumentException.class, () -> contactService.createContact(crudContact, 7));
  }
}
