package io.github.sambrodeur.resource.server.demo.contact.service;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.dto.CRUDContact;
import io.github.sambrodeur.resource.server.demo.contact.dto.ContactDB;
import org.instancio.Instancio;
import org.instancio.Select;

abstract class ContactServiceImplTest {

  CRUDContact getCRUDContact() {
    return Instancio.of(CRUDContact.class).create();
  }

  Contact getContact() {
    return Instancio.of(Contact.class).create();
  }

  ContactDB getContactDB() {
    return Instancio.of(ContactDB.class)
      .set(Select.field(ContactDB::getCompanyId), 1)
      .create();
  }
}
