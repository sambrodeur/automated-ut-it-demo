package io.github.sambrodeur.resource.server.demo.rest.api.contact.mapper;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.ContactType;
import io.github.sambrodeur.resource.server.demo.contact.dto.CRUDContact;
import io.github.sambrodeur.resource.server.demo.rest.api.common.ContactModel;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.CRUDContactModel;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.ContactTypeModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactModelMapper {

  public ContactModel map(Contact contact) {
    return new ContactModel(contact.getId(), ContactTypeModel.fromKey(contact.getContactType().getKey()), contact.getContactValue());
  }

  public CRUDContact map(CRUDContactModel crudContactModel) {
    CRUDContact crudContact = new CRUDContact();

    crudContact.setContactType(ContactType.fromKey(crudContactModel.contactType().getKey()));
    crudContact.setContactValue(crudContactModel.contactValue());

    return crudContact;
  }

  public List<ContactModel> map(List<Contact> contacts) {
    return contacts.stream()
      .map(this::map)
      .toList();
  }
}
