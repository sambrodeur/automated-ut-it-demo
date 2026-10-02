package io.github.sambrodeur.resource.server.demo.rest.api.company.controller;

import io.github.sambrodeur.resource.server.demo.company.Company;
import io.github.sambrodeur.resource.server.demo.company.CompanyType;
import io.github.sambrodeur.resource.server.demo.company.service.CompanyService;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CRUDCompanyModel;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyModel;
import io.github.sambrodeur.resource.server.demo.rest.api.company.CompanyTypeModel;
import io.github.sambrodeur.resource.server.demo.rest.api.company.mapper.CompanyModelMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
@Tag(name = "Company", description = "Company management APIs")
@Slf4j
public class CompanyController {

  private final CompanyService companyService;
  private final CompanyModelMapper companyModelMapper;

  @Operation(summary = "Create a new company", description = "Creates a new company with optional contacts. The creation and modification dates are set automatically.")
  @ApiResponse(responseCode = "201", description = "Company created successfully", content = @Content(schema = @Schema(implementation = CompanyModel.class)))
  @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
  @PostMapping(version = "1")
  public ResponseEntity<CompanyModel> createCompany(@RequestBody CRUDCompanyModel crudCompanyModel) {
    Company createdCompany = companyService.createCompany(companyModelMapper.map(crudCompanyModel));

    return ResponseEntity.status(HttpStatus.CREATED).body(companyModelMapper.map(createdCompany));
  }

  @Operation(summary = "Get company by ID", description = "Retrieves a company by its ID, including all associated contacts")
  @ApiResponse(responseCode = "200", description = "Company found", content = @Content(schema = @Schema(implementation = CompanyModel.class)))
  @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)
  @GetMapping(value = "/{id}", version = "1")
  public ResponseEntity<CompanyModel> getCompanyById(@PathVariable Integer id) {
    Company company = companyService.getCompanyById(id);

    return company != null ? ResponseEntity.ok(companyModelMapper.map(company)) : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Get all companies", description = "Retrieves a list of all companies with their associated contacts")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved list of companies", content = @Content(schema = @Schema(implementation = CompanyModel.class)))
  @GetMapping(version = "1")
  public ResponseEntity<List<CompanyModel>> getAllCompanies() {
    List<Company> companies = companyService.getAllCompanies();

    return ResponseEntity.ok(companyModelMapper.map(companies));
  }

  @Operation(summary = "Update a company", description = "Updates an existing company. The modification date is updated automatically.")
  @ApiResponse(responseCode = "200", description = "Company updated successfully", content = @Content(schema = @Schema(implementation = CompanyModel.class)))
  @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
  @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)
  @PutMapping(value = "/{id}", version = "1")
  public ResponseEntity<CompanyModel> updateCompany(@PathVariable Integer id, @RequestBody CRUDCompanyModel crudCompanyModel) {
    try {
      Company updatedCompany = companyService.updateCompany(id, companyModelMapper.map(crudCompanyModel));
      return ResponseEntity.ok(companyModelMapper.map(updatedCompany));
    } catch (IllegalArgumentException e) {
      log.info("Company not found", e);
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(summary = "Delete a company", description = "Deletes a company and all its associated contacts")
  @ApiResponse(responseCode = "204", description = "Company deleted successfully", content = @Content)
  @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)
  @DeleteMapping(value = "/{id}", version = "1")
  public ResponseEntity<Void> deleteCompany(@PathVariable Integer id) {
      companyService.deleteCompany(id);

      return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Get companies by type", description = "Retrieves all companies of a specific type")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved companies", content = @Content(schema = @Schema(implementation = CompanyModel.class)))
  @ApiResponse(responseCode = "400", description = "Invalid company type", content = @Content)
  @GetMapping(value = "/type/{companyTypeModel}", version = "1")
  public ResponseEntity<List<CompanyModel>> getCompaniesByType(@PathVariable CompanyTypeModel companyTypeModel) {
    List<Company> companies = companyService.getCompaniesByType(CompanyType.getBy(companyTypeModel.getKey()));

    return ResponseEntity.ok(companyModelMapper.map(companies));
  }
}
