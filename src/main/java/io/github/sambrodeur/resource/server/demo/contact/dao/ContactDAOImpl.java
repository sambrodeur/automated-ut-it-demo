package io.github.sambrodeur.resource.server.demo.contact.dao;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.ContactType;
import io.github.sambrodeur.resource.server.demo.contact.dto.ContactDB;
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
public class ContactDAOImpl implements ContactDAO {

  private final JdbcClient jdbcClient;

  @Override
  public Contact save(Contact contact, Integer companyId) {
    String sql = """
        INSERT INTO contact (company_id, contact_type, contact_value, creation_date, modification_date)
        VALUES (:COMPANY_ID, :CONTACT_TYPE, :CONTACT_VALUE, :CREATION_DATE, :MODIFICATION_DATE)
        """;

    KeyHolder keyHolder = new GeneratedKeyHolder();

    Date now = new Date();

    int result = jdbcClient.sql(sql)
      .param("CONTACT_TYPE", contact.getContactType().getKey())
      .param("CONTACT_VALUE", contact.getContactValue())
      .param("COMPANY_ID", companyId)
      .param("CREATION_DATE", now)
      .param("MODIFICATION_DATE", now)
      .update(keyHolder);

    if (result == 1) {
      Number key = keyHolder.getKey();
      contact.setId(key == null ? null : key.intValue());
      contact.setCreationDate(now);
      contact.setModificationDate(now);
    }

    return contact;
  }

  @Override
  public ContactDB findById(Integer id) {
    String sql = """
        SELECT *
        FROM contact
        WHERE id = ?
        """;

    return jdbcClient.sql(sql)
      .param(id)
      .query(this::mapRow)
      .single();
  }

  @Override
  public List<ContactDB> findAll() {
    String sql = """
        SELECT *
        FROM contact
        """;

    return jdbcClient.sql(sql)
      .query(this::mapRow)
      .list();
  }

  @Override
  public Contact update(Contact contact) {
    String sql = """
        UPDATE contact
        SET contact_type = :CONTACT_TYPE,
            contact_value = :CONTACT_VALUE,
            modification_date = :MODIFICATION_DATE
        WHERE id = :ID
        """;

    Date now = new Date();

    int result = jdbcClient.sql(sql)
      .param("CONTACT_TYPE", contact.getContactType().getKey())
      .param("CONTACT_VALUE", contact.getContactValue())
      .param("MODIFICATION_DATE", now)
      .param("ID", contact.getId())
      .update();

    if(result == 1) {
      contact.setModificationDate(now);
    }

    return contact;
  }

  @Override
  public void deleteById(Integer id) {
    String sql = "DELETE FROM contact WHERE id = :ID";

    jdbcClient.sql(sql)
      .param("ID", id)
      .update();
  }

  @Override
  public void deleteByCompanyId(Integer companyId) {
    String sql = "DELETE FROM contact WHERE company_id = :COMPANY_ID";

    jdbcClient.sql(sql)
      .param("COMPANY_ID", companyId)
      .update();
  }

  @Override
  public List<ContactDB> findByContactType(ContactType contactType) {
    String sql = """
        SELECT *
        FROM contact
        WHERE contact_type = ?
        """;

    return jdbcClient.sql(sql)
      .param(contactType.name())
      .query(this::mapRow)
      .list();
  }

  @Override
  public List<ContactDB> findByCompanyId(Integer companyId) {
    String sql = """
        SELECT *
        FROM contact
        WHERE company_id = :COMPANY_ID
        """;

    return jdbcClient.sql(sql)
      .param("COMPANY_ID", companyId)
      .query(this::mapRow)
      .list();
  }

  private ContactDB mapRow(ResultSet rs, int rowNum) throws SQLException {
    ContactDB contactDB = new ContactDB();
    contactDB.setCompanyId(rs.getInt("company_id"));

    Contact contact = new Contact();
    contact.setId(rs.getInt("id"));
    contact.setContactType(ContactType.fromKey(rs.getInt("contact_type")));
    contact.setContactValue(rs.getString("contact_value"));
    contact.setCreationDate(rs.getTimestamp("creation_date"));
    contact.setModificationDate(rs.getTimestamp("modification_date"));

    contactDB.setContact(contact);

    return contactDB;
  }
}
