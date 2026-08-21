package io.github.sambrodeur.resource.server.demo.contact.dao;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.simple.JdbcClient;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveTest extends ContactDAOImplTest {

  @InjectMocks
  private ContactDAOImpl contactDAO;

  @Mock
  private JdbcClient jdbcClient;

  @Mock
  private JdbcClient.StatementSpec statementSpec;

  private Contact contact;
  private Integer companyId;

  @BeforeEach
  void setUp() {
    contact = getContact();
    companyId = 1;

    when(jdbcClient.sql(anyString())).thenReturn(statementSpec);
    when(statementSpec.param(anyString(), any())).thenReturn(statementSpec);
  }

  @Test
  void saveSuccess() {
    when(statementSpec.update(any())).thenReturn(1);

    assertDoesNotThrow(() -> contactDAO.save(contact, companyId));
  }

  @Test
  void saveFail() {
    when(statementSpec.update(any())).thenReturn(0);

    assertDoesNotThrow(() -> contactDAO.save(contact, companyId));
  }
}
