package io.github.sambrodeur.resource.server.demo.contact.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DeleteByCompanyIdIntegrationTest extends ContactServiceImplIntegrationTest {

  @Autowired
  private ContactService contactService;

  private Integer id;

  @BeforeEach
  void setUp() {
    insertData();

    id = 2000;
  }

  @AfterEach
  void tearDown() {
    clearData();
  }

  @Test
  void deleteByCompanyIdSuccess() {
    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertEquals(CONTACT_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));

    contactService.deleteByCompanyId(id);

    assertEquals(COMPANY_COUNT, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_COMPANY));
    assertEquals(CONTACT_COUNT - 1, JdbcTestUtils.countRowsInTable(jdbcClient, TABLE_CONTACT));
  }
}
