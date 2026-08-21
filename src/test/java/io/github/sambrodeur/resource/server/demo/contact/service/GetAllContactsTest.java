package io.github.sambrodeur.resource.server.demo.contact.service;

import io.github.sambrodeur.resource.server.demo.contact.dao.ContactDAO;
import io.github.sambrodeur.resource.server.demo.contact.dto.ContactDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllContactsTest extends ContactServiceImplTest {

  @InjectMocks
  private ContactServiceImpl contactService;

  @Mock
  private ContactDAO contactDAO;

  private ContactDB contactDB;

  @BeforeEach
  void setUp() {
    contactDB = getContactDB();
  }

  @Test
  void getAllContactsSuccess() {
    when(contactDAO.findAll()).thenReturn(List.of(contactDB));

    assertNotNull(contactService.getAllContacts());
  }
}
