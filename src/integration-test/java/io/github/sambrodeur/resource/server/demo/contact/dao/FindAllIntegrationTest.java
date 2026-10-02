package io.github.sambrodeur.resource.server.demo.contact.dao;

import io.github.sambrodeur.resource.server.demo.contact.dto.ContactDB;
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
class FindAllIntegrationTest extends ContactDAOIntegrationTest {

  @Autowired
  private ContactDAO contactDAO;

  @BeforeEach
  void setUp() {
    insertData();
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void findAllSuccess() {
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    List<ContactDB> contactDBs = contactDAO.findAll();

    assertNotNull(contactDBs);
    assertFalse(contactDBs.isEmpty());
    assertEquals(CONTACT_COUNT, contactDBs.size());
    contactDBs.forEach(contactDB -> validateContact(contactDB.getContact()));
  }
}
