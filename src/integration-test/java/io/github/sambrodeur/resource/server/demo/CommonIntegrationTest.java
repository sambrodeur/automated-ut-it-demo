package io.github.sambrodeur.resource.server.demo;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.CompanyType;
import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.ContactType;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This class is used to keep common method / objects for all tests.
 *
 * Creation of data for the test should not be added in the common. All tests packages need to keep an independent structure
 * to keep maintainability of the tests as low as possible.
 */
public abstract class CommonIntegrationTest {

  @Autowired
  protected JdbcClient jdbcClient;

  protected static final int DEFAULT_COMPANY_COUNT = 0;
  protected static final int DEFAULT_CONTACT_COUNT = 0;

  protected static final String TABLE_COMPANY = "company";
  protected static final String TABLE_CONTACT = "contact";

  private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  protected void clearData() {
    JdbcTestUtils.deleteFromTables(jdbcClient, TABLE_CONTACT, TABLE_COMPANY);
  }

  protected void validateCompany(Company company) {
    Company companyDB = jdbcClient
      .sql("SELECT * FROM " + TABLE_COMPANY + " WHERE id = :ID")
      .param("ID", company.getId())
      .query((rs, rowNum) -> {
        Company companyRS = new Company();

        companyRS.setId(rs.getInt("id"));
        companyRS.setName(rs.getString("name"));
        companyRS.setCompanyType(CompanyType.getBy(rs.getInt("company_type")));
        companyRS.setCreationDate(rs.getTimestamp("creation_date"));
        companyRS.setModificationDate(rs.getTimestamp("modification_date"));

        return companyRS;
      })
      .single();

    assertNotNull(companyDB);
    assertEquals(company.getId(), companyDB.getId());
    assertEquals(company.getName(), companyDB.getName());
    assertEquals(company.getCompanyType(), companyDB.getCompanyType());
    assertEquals(dateFormat.format(company.getCreationDate()), dateFormat.format(companyDB.getCreationDate()));
    assertEquals(company.getModificationDate(), companyDB.getModificationDate());
  }

  protected void validateContact(Contact contact) {
    Contact contactDB = jdbcClient
      .sql("SELECT * FROM " + TABLE_CONTACT + " WHERE id = :ID")
      .param("ID", contact.getId())
      .query((rs, rowNum) -> {
        Contact contactRS = new Contact();

        contactRS.setId(rs.getInt("id"));
        contactRS.setContactType(ContactType.fromKey(rs.getInt("contact_type")));
        contactRS.setContactValue(rs.getString("contact_value"));
        contactRS.setCreationDate(rs.getTimestamp("creation_date"));
        contactRS.setModificationDate(rs.getTimestamp("modification_date"));

        return contactRS;
      })
      .single();

    assertNotNull(contactDB);
    assertEquals(contact.getId(), contactDB.getId());
    assertEquals(contact.getContactType(), contactDB.getContactType());
    assertEquals(contact.getContactValue(), contactDB.getContactValue());
    assertEquals(dateFormat.format(contact.getCreationDate()), dateFormat.format(contactDB.getCreationDate()));
    assertEquals(contact.getModificationDate(), contactDB.getModificationDate());
  }

  protected void validateNbContacts(int expectedNbContacts, List<CompanyModel> companyModels) {
    int nbContacts = companyModels.stream().mapToInt(c -> c.contacts().size()).sum();
    assertEquals(expectedNbContacts, nbContacts);
  }
}
