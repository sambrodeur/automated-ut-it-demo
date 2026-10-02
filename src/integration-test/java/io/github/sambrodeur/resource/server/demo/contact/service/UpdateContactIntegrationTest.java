package io.github.sambrodeur.resource.server.demo.contact.service;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.dto.CRUDContact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UpdateContactIntegrationTest extends ContactServiceImplIntegrationTest {

  @Autowired
  private ContactService contactService;

  private CRUDContact crudContact;
  private Integer id;
  private Integer companyId;

  @BeforeEach
  void setUp() {
    insertData();

    crudContact = getCRUDContact();
    id = 1000;
    companyId = 2000;
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void updateCompanySuccess() {
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    Contact contact = contactService.updateContact(companyId, id, crudContact);

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
    assertNotNull(contact);
    validateContact(contact);
  }

  @Test
  void updateCompanyWrongId() {
    id = 2000;

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    assertThrows(IllegalArgumentException.class, () -> contactService.updateContact(companyId, id, crudContact));

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
  }
}
