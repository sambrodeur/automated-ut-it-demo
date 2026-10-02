package io.github.sambrodeur.resource.server.demo.contact.dao;

import io.github.sambrodeur.resource.server.demo.contact.dto.ContactDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FindByIdIntegrationTest extends ContactDAOIntegrationTest {

  @Autowired
  private ContactDAO contactDAO;

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
  void findByIdSuccess() {
    ContactDB contactDB = contactDAO.findById(id);

    assertNotNull(contactDB);
    validateContact(contactDB.getContact());
  }
}
