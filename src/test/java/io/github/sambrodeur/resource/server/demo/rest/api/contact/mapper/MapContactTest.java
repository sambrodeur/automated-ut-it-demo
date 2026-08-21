package io.github.sambrodeur.resource.server.demo.rest.api.contact.mapper;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.rest.api.common.ContactModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MapContactTest extends ContactModelMapperTest {

  @InjectMocks
  private ContactModelMapper contactModelMapper;

  private Contact contact;

  @BeforeEach
  void setUp() {
    contact = getContact();
  }

  @Test
  void mapContactSuccess() {
    ContactModel contactModel = contactModelMapper.map(contact);

    assertNotNull(contactModel);
    assertEquals(contact.getId(), contactModel.id());
    assertEquals(contact.getContactType().getKey(), contactModel.contactType().getKey());
    assertEquals(contact.getContactValue(), contactModel.contactValue());
  }
}
