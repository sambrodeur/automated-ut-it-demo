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
class UpdateIntegrationTest extends ContactDAOIntegrationTest {

  @Autowired
  private ContactDAO contactDAO;

  private Contact contact;

  @BeforeEach
  void setUp() {
    insertData();

    contact = getContact();
    contact.setId(1000);
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void updateSuccess() {
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    contactDAO.update(contact);

    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
    assertNotNull(contact);
    validateContact(contact);
  }

}
