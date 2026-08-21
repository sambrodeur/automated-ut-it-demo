package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.CompanyType;
import io.github.sambrodeur.resource.server.demo.company.dao.CompanyDAO;
import io.github.sambrodeur.resource.server.demo.company.dto.CRUDCompany;
import io.github.sambrodeur.resource.server.demo.contact.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyDAO companyDAO;
  private final ContactService contactService;

  @Override
  @Transactional
  public Company createCompany(CRUDCompany crudCompany) {
    Company company = create(crudCompany);

    companyDAO.save(company);

    return company;
  }

  @Override
  public Company getCompanyById(Integer id) {
    Company company = companyDAO.findById(id);

    if(company != null) {
      company.setContacts(contactService.getContactsByCompanyId(company.getId()));
    }

    return company;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Company> getAllCompanies() {
    List<Company> companies = companyDAO.findAll();

    companies.forEach(company -> company.setContacts(contactService.getContactsByCompanyId(company.getId())));

    return companies;
  }

  @Override
  @Transactional
  public Company updateCompany(Integer id, CRUDCompany crudCompany) {
    Company existingCompany = getCompanyById(id);

    if (existingCompany == null) {
      throw new IllegalArgumentException("Company with id " + id + " not found");
    }

    existingCompany.setName(crudCompany.getName());
    existingCompany.setCompanyType(crudCompany.getCompanyType());

    companyDAO.update(existingCompany);

    return existingCompany;
  }

  @Override
  @Transactional
  public void deleteCompany(Integer id) {
    contactService.deleteByCompanyId(id);
    companyDAO.deleteById(id);
  }

  @Override
  public List<Company> getCompaniesByType(CompanyType companyType) {
    List<Company> companies = companyDAO.findByCompanyType(companyType);

    companies.forEach(company -> company.setContacts(contactService.getContactsByCompanyId(company.getId())));

    return companies;
  }

  private Company create(CRUDCompany crudCompany) {
    Company company = new Company();

    company.setName(crudCompany.getName());
    company.setCompanyType(crudCompany.getCompanyType());

    return company;
  }
}
