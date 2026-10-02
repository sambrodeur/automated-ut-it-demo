package io.github.sambrodeur.resource.server.demo.company.dao;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.CompanyType;

import java.util.List;

public interface CompanyDAO {

  Company save(Company company);

  Company findById(Integer id);

  List<Company> findAll();

  void update(Company company);

  void deleteById(Integer id);

  List<Company> findByCompanyType(CompanyType companyType);
}
