package io.github.sambrodeur.resource.server.demo.contact.dao;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.dto.ContactDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllTest extends ContactDAOImplTest {

  @InjectMocks
  private ContactDAOImpl contactDAO;

  @Mock
  private JdbcClient jdbcClient;

  @Mock
  private JdbcClient.StatementSpec statementSpec;

  @Mock
  private JdbcClient.MappedQuerySpec<ContactDB> mapQuerySpec;

  @Mock
  private ResultSet resultSet;

  @BeforeEach
  void setUp() throws SQLException {
    Contact contact = getContact();

    when(resultSet.getInt("company_id")).thenReturn(1);
    when(resultSet.getInt("id")).thenReturn(contact.getId());
    when(resultSet.getInt("contact_type")).thenReturn(contact.getContactType().getKey());
    when(resultSet.getString("contact_value")).thenReturn(contact.getContactValue());
    when(resultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(contact.getCreationDate().getTime()));
    when(resultSet.getTimestamp("modification_date")).thenReturn(new Timestamp(contact.getModificationDate().getTime()));

    when(jdbcClient.sql(anyString())).thenReturn(statementSpec);
    when(statementSpec.query(ArgumentMatchers.<RowMapper<ContactDB>>any())).thenAnswer(invocation -> {
      RowMapper<Company> rowMapper = invocation.getArgument(0);
      rowMapper.mapRow(resultSet, 0);
      return mapQuerySpec;
    });
    when(mapQuerySpec.list()).thenReturn(List.of(getContactDB()));
  }

  @Test
  void findAllSuccess() {
    assertNotNull(contactDAO.findAll());
  }
}
