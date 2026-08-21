package io.github.sambrodeur.resource.server.demo.contact.dao;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.dto.ContactDB;
import org.instancio.Instancio;

abstract class ContactDAOImplTest {

  Contact getContact() {
    return Instancio.of(Contact.class).create();
  }

  ContactDB getContactDB() {
    return Instancio.of(ContactDB.class).create();
  }
}
