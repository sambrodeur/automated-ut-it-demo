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

@SpringBootTest
class CreateContactIntegrationTest extends ContactServiceImplIntegrationTest {

  @Autowired
  private ContactService contactService;

  private CRUDContact crudContact;
  private Integer id;

  @BeforeEach
  void setUp() {
    insertData();

    crudContact = getCRUDContact();
    id = 1000;
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void createCompanySuccess() {
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    Contact contact = contactService.createContact(crudContact, id);

    assertEquals(CONTACT_COUNT + 1, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
    assertNotNull(contact);
    validateContact(contact);
  }
}