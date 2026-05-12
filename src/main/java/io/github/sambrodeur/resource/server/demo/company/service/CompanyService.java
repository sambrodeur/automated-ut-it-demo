package io.github.sambrodeur.resource.server.demo.company.service;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.CompanyType;
import io.github.sambrodeur.resource.server.demo.company.dto.CRUDCompany;
import io.github.sambrodeur.resource.server.demo.contact.Contact;

import java.util.List;

public interface CompanyService {

  Company createCompany(CRUDCompany crudCompany);

  Company getCompanyById(Integer id);

  List<Company> getAllCompanies();

  Company updateCompany(Integer id, CRUDCompany crudCompany);

  void deleteCompany(Integer id);

  List<Company> getCompaniesByType(CompanyType companyType);
}
