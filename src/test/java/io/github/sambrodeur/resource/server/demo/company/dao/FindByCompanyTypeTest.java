package io.github.sambrodeur.resource.server.demo.company.dao;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.CompanyType;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindByCompanyTypeTest extends CompanyDAOImplTest {

  @InjectMocks
  private CompanyDAOImpl companyDAO;

  @Mock
  private JdbcClient jdbcClient;

  @Mock
  private JdbcClient.StatementSpec statementSpec;

  @Mock
  private JdbcClient.MappedQuerySpec<Company> mapQuerySpec;

  @Mock
  private ResultSet resultSet;

  private CompanyType companyType;

  @BeforeEach
  void setUp() throws SQLException {
    companyType = CompanyType.FOOD;

    Company company = getCompany();

    when(resultSet.getInt("id")).thenReturn(company.getId());
    when(resultSet.getString("name")).thenReturn(company.getName());
    when(resultSet.getInt("company_type")).thenReturn(company.getCompanyType().getKey());
    when(resultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(company.getCreationDate().getTime()));
    when(resultSet.getTimestamp("modification_date")).thenReturn(new Timestamp(company.getModificationDate().getTime()));

    when(jdbcClient.sql(anyString())).thenReturn(statementSpec);
    when(statementSpec.param(anyString(), any())).thenReturn(statementSpec);
    when(statementSpec.query(ArgumentMatchers.<RowMapper<Company>>any())).thenAnswer(invocation -> {
      RowMapper<Company> rowMapper = invocation.getArgument(0);
      rowMapper.mapRow(resultSet, 0);
      return mapQuerySpec;
    });
    when(mapQuerySpec.list()).thenReturn(List.of(getCompany()));
  }

  @Test
  void findByCompanyTypeSuccess() {
    assertNotNull(companyDAO.findByCompanyType(companyType));
  }
}
