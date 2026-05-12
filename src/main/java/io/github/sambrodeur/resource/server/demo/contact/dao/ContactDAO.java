package io.github.sambrodeur.resource.server.demo.contact.dao;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.ContactType;
import io.github.sambrodeur.resource.server.demo.contact.dto.ContactDB;

import java.util.List;

public interface ContactDAO {

  Contact save(Contact contact, Integer companyId);

  ContactDB findById(Integer id);

  List<ContactDB> findAll();

  Contact update(Contact contact);

  void deleteById(Integer id);

  void deleteByCompanyId(Integer companyId);

  List<ContactDB> findByContactType(ContactType contactType);

  List<ContactDB> findByCompanyId(Integer companyId);
}
