package io.github.sambrodeur.resource.server.demo.rest.api.contact.mapper;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.rest.api.common.ContactModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MapContactsTest extends ContactModelMapperTest {

  @InjectMocks
  private ContactModelMapper contactModelMapper;

  private List<Contact> contacts;

  @BeforeEach
  void setUp() {
    contacts = getContacts();
  }

  @Test
  void mapContactsSuccess() {
    List<ContactModel> contactsModel = contactModelMapper.map(contacts);

    assertNotNull(contactsModel);
    assertFalse(contactsModel.isEmpty());
  }
}