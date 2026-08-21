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
class UpdateTest extends ContactDAOImplTest {

  @InjectMocks
  private ContactDAOImpl contactDAO;

  @Mock
  private JdbcClient jdbcClient;

  @Mock
  private JdbcClient.StatementSpec statementSpec;

  private Contact contact;

  @BeforeEach
  void setUp() {
    contact = getContact();

    when(jdbcClient.sql(anyString())).thenReturn(statementSpec);
    when(statementSpec.param(anyString(), any())).thenReturn(statementSpec);
  }

  @Test
  void updateSuccess() {
    when(statementSpec.update()).thenReturn(1);

    assertDoesNotThrow(() -> contactDAO.update(contact));
  }

  @Test
  void updateFail() {
    when(statementSpec.update()).thenReturn(0);

    assertDoesNotThrow(() -> contactDAO.update(contact));
  }
}
