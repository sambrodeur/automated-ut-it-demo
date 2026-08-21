package io.github.sambrodeur.resource.server.demo.rest.api.contact.controller;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.service.ContactService;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.mapper.ContactModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetContactByIdTest extends ContactControllerTest {

  @InjectMocks
  private ContactController contactController;

  @Mock
  private ContactService contactService;

  @Mock
  private ContactModelMapper contactModelMapper;

  private Contact contact;

  @BeforeEach
  void setUp() {
    contact = getContact();
  }

  @Test
  void getContactByIdSuccess() {
    when(contactService.getContactById(1, 1)).thenReturn(contact);
    when(contactModelMapper.map(contact)).thenReturn(getContactModel());

    assertNotNull(contactController.getContactById(1, 1));
  }

  @Test
  void getContactByIdFail() {
    when(contactService.getContactById(1, 1)).thenReturn(null);

    assertNotNull(contactController.getContactById(1, 1));
  }
}
