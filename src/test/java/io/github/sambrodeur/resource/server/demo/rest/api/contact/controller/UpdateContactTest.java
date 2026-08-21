package io.github.sambrodeur.resource.server.demo.rest.api.contact.controller;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.service.ContactService;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.CRUDContactModel;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.mapper.ContactModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateContactTest extends ContactControllerTest {

  @InjectMocks
  private ContactController contactController;

  @Mock
  private ContactService contactService;

  @Mock
  private ContactModelMapper contactModelMapper;

  private CRUDContactModel crudContactModel;

  @BeforeEach
  void setUp() {
    crudContactModel = getCRUDContactModel();
    Contact contact = getContact();

    when(contactModelMapper.map(crudContactModel)).thenReturn(getCRUDContact());
    when(contactService.updateContact(eq(1), eq(1), any())).thenReturn(contact);
    when(contactModelMapper.map(contact)).thenReturn(getContactModel());
  }

  @Test
  void updateContactSuccess() {
    assertNotNull(contactController.updateContact(1, 1, crudContactModel));
  }
}
