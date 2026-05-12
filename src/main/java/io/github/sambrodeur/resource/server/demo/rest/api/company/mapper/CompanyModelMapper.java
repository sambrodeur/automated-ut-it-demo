package io.github.sambrodeur.resource.server.demo.rest.api.company.mapper;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.CompanyType;
import io.github.sambrodeur.resource.server.demo.company.dto.CRUDCompany;
import io.github.sambrodeur.resource.server.demo.rest.api.common.ContactModel;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyModel;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyTypeModel;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CRUDCompanyModel;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.ContactTypeModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyModelMapper {

  public CompanyModel map(Company company) {
    List<ContactModel> contacts = company.getContacts().stream()
      .map(contact -> new ContactModel(contact.getId(), ContactTypeModel.fromKey(contact.getContactType().getKey()), contact.getContactValue()))
      .toList();

    return new CompanyModel(company.getId(), company.getName(), CompanyTypeModel.getBy(company.getCompanyType().getKey()), contacts);
  }

  public CRUDCompany map(CRUDCompanyModel crudCompanyModel) {
    CRUDCompany crudCompany = new CRUDCompany();

    crudCompany.setName(crudCompanyModel.name());
    crudCompany.setCompanyType(CompanyType.getBy(crudCompanyModel.companyType().getKey()));

    return crudCompany;
  }

  public List<CompanyModel> map(List<Company> companies) {
    return companies.stream()
      .map(this::map)
      .toList();
  }
}
