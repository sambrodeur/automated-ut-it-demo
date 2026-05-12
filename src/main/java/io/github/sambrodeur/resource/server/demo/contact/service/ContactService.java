package io.github.sambrodeur.resource.server.demo.contact.service;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.ContactType;
import io.github.sambrodeur.resource.server.demo.contact.dto.CRUDContact;

import java.util.List;

public interface ContactService {

  Contact createContact(CRUDContact crudContact, Integer companyId);

  Contact getContactById(Integer companyId, Integer id);

  List<Contact> getAllContacts();

  Contact updateContact(Integer companyId, Integer id, CRUDContact crudContact);

  void deleteContact(Integer id);

  void deleteByCompanyId(Integer companyId);

  List<Contact> getContactsByCompanyId(Integer companyId);
}
