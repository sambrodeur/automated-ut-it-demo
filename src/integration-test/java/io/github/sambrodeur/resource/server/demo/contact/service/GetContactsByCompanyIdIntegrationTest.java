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

@SpringBootTest
class GetContactsByCompanyIdIntegrationTest extends ContactServiceImplIntegrationTest {

  @Autowired
  private ContactService contactService;

  private Integer id;

  @BeforeEach
  void setUp() {
    insertData();

    id = 1000;
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void getContactByIdSuccess() {
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    List<Contact> contacts = contactService.getContactsByCompanyId(id);

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
    contacts.forEach(this::validateContact);
  }
}
