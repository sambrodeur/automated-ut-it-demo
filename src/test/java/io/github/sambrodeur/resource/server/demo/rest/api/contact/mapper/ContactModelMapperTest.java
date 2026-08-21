package io.github.sambrodeur.resource.server.demo.rest.api.contact.mapper;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.CRUDContactModel;
import org.instancio.Instancio;

import java.util.List;

class ContactModelMapperTest {

  Contact getContact() {
    return Instancio.of(Contact.class).create();
  }

  CRUDContactModel getCRUDContactModel() {
    return Instancio.of(CRUDContactModel.class).create();
  }

  List<Contact> getContacts() {
    return List.of(Instancio.of(Contact.class).create());
  }

//  private ContactModelMapper contactModelMapper;
//
//  @BeforeEach
//  void setUp() {
//    contactModelMapper = new ContactModelMapper();
//  }
//
//  @Test
//  void mapContactSuccess() {
//    Contact contact = Instancio.of(Contact.class).create();
//
//    assertNotNull(contactModelMapper.map(contact));
//  }
//
//  @Test
//  void mapCrudContactModelSuccess() {
//    CRUDContactModel crudContactModel = Instancio.of(CRUDContactModel.class).create();
//
//    assertNotNull(contactModelMapper.map(crudContactModel));
//  }
//
//  @Test
//  void mapListContactSuccess() {
//    List<Contact> contacts = List.of(Instancio.of(Contact.class).create());
//
//    assertNotNull(contactModelMapper.map(contacts));
//  }
}
