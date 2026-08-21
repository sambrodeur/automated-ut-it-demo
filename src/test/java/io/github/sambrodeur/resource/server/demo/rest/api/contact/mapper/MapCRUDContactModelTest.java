package io.github.sambrodeur.resource.server.demo.rest.api.contact.mapper;

import io.github.sambrodeur.resource.server.demo.contact.dto.CRUDContact;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.CRUDContactModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MapCRUDContactModelTest extends ContactModelMapperTest {

  @InjectMocks
  private ContactModelMapper contactModelMapper;

  private CRUDContactModel crudContactModel;

  @BeforeEach
  void setUp() {
    crudContactModel = getCRUDContactModel();
  }

  @Test
  void mapCRUDContactModelSuccess() {
    CRUDContact crudContact = contactModelMapper.map(crudContactModel);

    assertNotNull(crudContact);
    assertEquals(crudContactModel.contactType().getKey(), crudContact.getContactType().getKey());
    assertEquals(crudContactModel.contactValue(), crudContact.getContactValue());
  }
}
