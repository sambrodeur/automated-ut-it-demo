package io.github.sambrodeur.resource.server.demo.company.dao;

import io.github.sambrodeur.resource.server.demo.company.Company;
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
class UpdateTest extends CompanyDAOImplTest {

  @InjectMocks
  private CompanyDAOImpl companyDAO;

  @Mock
  private JdbcClient jdbcClient;

  @Mock
  private JdbcClient.StatementSpec statementSpec;

  private Company company;

  @BeforeEach
  void setUp() {
    company = getCompany();

    when(jdbcClient.sql(anyString())).thenReturn(statementSpec);
    when(statementSpec.param(anyString(), any())).thenReturn(statementSpec);
  }

  @Test
  void updateSuccess() {
    when(statementSpec.update()).thenReturn(1);

    assertDoesNotThrow(() -> companyDAO.update(company));
  }

  @Test
  void updateFail() {
    when(statementSpec.update()).thenReturn(0);

    assertDoesNotThrow(() -> companyDAO.update(company));
  }
}
