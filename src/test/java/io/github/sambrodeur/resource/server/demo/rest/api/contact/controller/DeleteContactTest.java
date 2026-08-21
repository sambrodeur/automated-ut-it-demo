package io.github.sambrodeur.resource.server.demo.rest.api.contact.controller;

import io.github.sambrodeur.resource.server.demo.contact.service.ContactService;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.mapper.ContactModelMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class DeleteContactTest extends ContactControllerTest {

  @InjectMocks
  private ContactController contactController;

  @Mock
  private ContactService contactService;

  @Mock
  private ContactModelMapper contactModelMapper;

  @Test
  void deleteContactSuccess() {
    assertNotNull(contactController.deleteContact(1, 1));
  }
}
