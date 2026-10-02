package io.github.sambrodeur.resource.server.demo.contact.service;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GetAllContactsIntegrationTest extends ContactServiceImplIntegrationTest {

  @Autowired
  private ContactService contactService;

  @BeforeEach
  void setUp() {
    insertData();
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void getAllContactsSuccess() {
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    List<Contact> contacts = contactService.getAllContacts();

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
    assertNotNull(contacts);
    assertFalse(contacts.isEmpty());
    assertEquals(CONTACT_COUNT, contacts.size());
    contacts.forEach(this::validateContact);
  }
}
