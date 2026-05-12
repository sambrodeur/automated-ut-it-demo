package io.github.sambrodeur.resource.server.demo.contact.service;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.ContactType;
import io.github.sambrodeur.resource.server.demo.contact.dao.ContactDAO;
import io.github.sambrodeur.resource.server.demo.contact.dto.CRUDContact;
import io.github.sambrodeur.resource.server.demo.contact.dto.ContactDB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

  private final ContactDAO contactDAO;

  @Override
  @Transactional
  public Contact createContact(CRUDContact crudContact, Integer companyId) {
    List<ContactDB> existingContacts = contactDAO.findByCompanyId(companyId);

    if(existingContacts.stream().anyMatch(contactDB -> contactDB.getContact().getContactType() == crudContact.getContactType())) {
      throw new IllegalArgumentException("Contact with type " + crudContact.getContactType() + " already exists for company with id " + companyId);
    }

    Contact contact = new Contact();

    contact.setContactType(crudContact.getContactType());
    contact.setContactValue(crudContact.getContactValue());

    return contactDAO.save(contact, companyId);
  }

  @Override
  @Transactional(readOnly = true)
  public Contact getContactById(Integer companyId, Integer id) {
    ContactDB contactDB = contactDAO.findById(id);

    if (contactDB == null) {
      throw new IllegalArgumentException("Contact with id " + id + " not found");
    }

    if(!contactDB.getCompanyId().equals(companyId)) {
      throw new IllegalArgumentException("Contact with id " + id + " does not belong to company with id " + companyId);
    }

    return contactDB.getContact();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Contact> getAllContacts() {
    List<ContactDB> contactDBs = contactDAO.findAll();

    return convert(contactDBs);
  }

  @Override
  @Transactional
  public Contact updateContact(Integer companyId, Integer id, CRUDContact crudContact) {
    List<ContactDB> existingContacts = contactDAO.findByCompanyId(companyId);

    if (existingContacts == null) {
      throw new IllegalArgumentException("No contacts found for company with id " + companyId);
    }

    ContactDB contactDB = existingContacts.stream().filter(contactFilter -> contactFilter.getContact().getId().equals(id))
      .findFirst()
      .orElse(null);

    if (contactDB == null) {
      throw new IllegalArgumentException("Contact with id " + id + " not found");
    }

    contactDB.getContact().setContactValue(crudContact.getContactValue());
    contactDB.getContact().setContactType(crudContact.getContactType());

    return contactDAO.update(contactDB.getContact());
  }

  @Override
  @Transactional
  public void deleteContact(Integer id) {
    contactDAO.deleteById(id);
  }

  @Override
  public void deleteByCompanyId(Integer companyId) {
    contactDAO.deleteByCompanyId(companyId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Contact> getContactsByCompanyId(Integer companyId) {
    List<ContactDB> contactDBs = contactDAO.findByCompanyId(companyId);

    return convert(contactDBs);
  }

  private List<Contact> convert(List<ContactDB> contactDBs) {
    if (contactDBs.isEmpty()) {
      return List.of();
    }

    return contactDBs.stream()
      .map(ContactDB::getContact)
      .toList();
  }
}
