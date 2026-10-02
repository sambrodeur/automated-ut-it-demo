package io.github.sambrodeur.resource.server.demo.contact.service;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GetContactByIdIntegrationTest extends ContactServiceImplIntegrationTest {

  @Autowired
  private ContactService contactService;

  private Integer id;
  private Integer companyId;

  @BeforeEach
  void setUp() {
    insertData();

    id = 1000;
    companyId = 2000;
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void getContactByIdSuccess() {
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    Contact contact = contactService.getContactById(companyId, id);

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
    validateContact(contact);
  }

  @Test
  void getContactByIdCompanyNotFound() {
    companyId = 1;

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    assertThrows(IllegalArgumentException.class, () -> contactService.getContactById(companyId, id));

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
  }
}
