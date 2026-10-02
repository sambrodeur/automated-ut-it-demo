package io.github.sambrodeur.resource.server.demo.contact.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DeleteByIdIntegrationTest extends ContactDAOIntegrationTest {

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
  void deleteByIdSuccess() {
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    contactDAO.deleteById(id);

    assertEquals(CONTACT_COUNT - 1, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
  }
}
