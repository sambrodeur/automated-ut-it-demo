package io.github.sambrodeur.resource.server.demo.contact.dao;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SaveIntegrationTest extends ContactDAOIntegrationTest {

  @Autowired
  private ContactDAO contactDAO;

  private Contact contact;
  private Integer id;

  @BeforeEach
  void setUp() {
    insertData();

    contact = getContact();
    id = 1000;
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void saveSuccess() {
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    Contact contactSaved = contactDAO.save(contact, id);

    assertEquals(CONTACT_COUNT + 1, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
    assertNotNull(contactSaved);
    validateContact(contactSaved);
  }

  @Test
  void saveWithDataSuccess() {
    id = 2000;

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    Contact contactSaved = contactDAO.save(contact, id);

    assertEquals(CONTACT_COUNT + 1, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
    assertNotNull(contactSaved);
    validateContact(contactSaved);
  }
}
