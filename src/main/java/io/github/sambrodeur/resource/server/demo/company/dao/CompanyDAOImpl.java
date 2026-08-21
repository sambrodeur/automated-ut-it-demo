package io.github.sambrodeur.resource.server.demo.company.dao;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.CompanyType;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyDAOImpl implements CompanyDAO {

  private final JdbcClient jdbcClient;

  @Override
  public void save(Company company) {
    String sql = """
        INSERT INTO company (name, company_type, creation_date, modification_date)
        VALUES (:NAME, :COMPANY_TYPE, :CREATION_DATE, :MODIFICATION_DATE)
        """;

    KeyHolder keyHolder = new GeneratedKeyHolder();
    Date now = new Date();

    int result = jdbcClient.sql(sql)
      .param("NAME", company.getName())
      .param("COMPANY_TYPE", company.getCompanyType().getKey())
      .param("CREATION_DATE", now)
      .param("MODIFICATION_DATE", now)
      .update(keyHolder);

    if (result == 1) {
      Number key = keyHolder.getKey();
      company.setId(key == null ? null : key.intValue());
      company.setCreationDate(now);
      company.setModificationDate(now);
    }
  }

  @Override
  public Company findById(Integer id) {
    String sql = """
        SELECT *
        FROM company
        WHERE id = :ID
        """;

    return jdbcClient.sql(sql)
      .param("ID", id)
      .query(this::mapRow)
      .single();
  }

  @Override
  public List<Company> findAll() {
    String sql = """
        SELECT id, name, company_type, creation_date, modification_date
        FROM company
        """;

    return jdbcClient.sql(sql)
      .query(this::mapRow)
      .list();
  }

  @Override
  public void update(Company company) {
    String sql = """
        UPDATE company
        SET name = :NAME,
            company_type = :COMPANY_TYPE,
            modification_date = :MODIFICATION_DATE
        WHERE id = :ID
        """;

    Date now = new Date();

    int result = jdbcClient.sql(sql)
      .param("NAME", company.getName())
      .param("COMPANY_TYPE", company.getCompanyType().getKey())
      .param("MODIFICATION_DATE", now)
      .param("ID", company.getId())
      .update();

    if(result == 1) {
      company.setModificationDate(now);
    }
  }

  @Override
  public void deleteById(Integer id) {
    String sql = "DELETE FROM company WHERE id = ?";

    jdbcClient.sql(sql)
      .param(id)
      .update();
  }

  @Override
  public List<Company> findByCompanyType(CompanyType companyType) {
    String sql = """
        SELECT *
        FROM company
        WHERE company_type = :COMPANY_TYPE
        """;

    return jdbcClient.sql(sql)
      .param("COMPANY_TYPE", companyType.getKey())
      .query(this::mapRow)
      .list();
  }

  private Company mapRow(ResultSet rs, int rowNum) throws SQLException {
    Company company = new Company();

    company.setId(rs.getInt("id"));
    company.setName(rs.getString("name"));
    company.setCompanyType(CompanyType.getBy(rs.getInt("company_type")));
    company.setCreationDate(rs.getTimestamp("creation_date"));
    company.setModificationDate(rs.getTimestamp("modification_date"));

    return company;
  }
}
