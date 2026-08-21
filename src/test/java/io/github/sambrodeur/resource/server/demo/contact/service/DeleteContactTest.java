package io.github.sambrodeur.resource.server.demo.contact.service;

import io.github.sambrodeur.resource.server.demo.contact.dao.ContactDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class DeleteContactTest extends ContactServiceImplTest {

  @InjectMocks
  private ContactServiceImpl contactService;

  @Mock
  private ContactDAO contactDAO;

  private Integer id;

  @BeforeEach
  void setUp() {
    id = 1;
  }

  @Test
  void deleteContactSuccess() {
    assertDoesNotThrow(() -> contactService.deleteContact(id));
  }
}
