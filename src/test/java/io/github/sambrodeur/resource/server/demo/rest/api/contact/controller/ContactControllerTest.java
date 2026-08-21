package io.github.sambrodeur.resource.server.demo.rest.api.contact.controller;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.dto.CRUDContact;
import io.github.sambrodeur.resource.server.demo.rest.api.common.ContactModel;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.CRUDContactModel;
import org.instancio.Instancio;

abstract class ContactControllerTest {

  CRUDContactModel getCRUDContactModel() {
    return Instancio.of(CRUDContactModel.class).create();
  }

  Contact getContact() {
    return Instancio.of(Contact.class).create();
  }

  CRUDContact getCRUDContact() {
    return Instancio.of(CRUDContact.class).create();
  }

  ContactModel getContactModel() {
    return Instancio.of(ContactModel.class).create();
  }
}
