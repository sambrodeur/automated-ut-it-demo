package io.github.sambrodeur.resource.server.demo.rest.api.contact.controller;

import io.github.sambrodeur.resource.server.demo.contact.Contact;
import io.github.sambrodeur.resource.server.demo.contact.service.ContactService;
import io.github.sambrodeur.resource.server.demo.rest.api.common.ContactModel;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.CRUDContactModel;
import io.github.sambrodeur.resource.server.demo.rest.api.contact.mapper.ContactModelMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/companies/{companyId}/contacts")
@RequiredArgsConstructor
@Tag(name = "Contact", description = "Contact management APIs")
public class ContactController {

  private final ContactService contactService;
  private final ContactModelMapper contactModelMapper;

  @Operation(summary = "Create a new contact", description = "Creates a new contact for a company. The creation and modification dates are set automatically.")
  @ApiResponse(responseCode = "201", description = "Contact created successfully", content = @Content(schema = @Schema(implementation = ContactModel.class)))
  @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
  @PostMapping(version = "1")
  public ResponseEntity<ContactModel> createContact(@PathVariable Integer companyId, @RequestBody CRUDContactModel crudContactModel) {
    Contact createdContact = contactService.createContact(contactModelMapper.map(crudContactModel), companyId);

    return ResponseEntity.status(HttpStatus.CREATED).body(contactModelMapper.map(createdContact));
  }

  @Operation(summary = "Get contact by ID", description = "Retrieves a contact by its ID")
  @ApiResponse(responseCode = "200", description = "Contact found", content = @Content(schema = @Schema(implementation = ContactModel.class)))
  @ApiResponse(responseCode = "404", description = "Contact not found", content = @Content)
  @GetMapping(value = "/{id}", version = "1")
  public ResponseEntity<ContactModel> getContactById(@PathVariable Integer companyId, @PathVariable Integer id) {
    Contact contact = contactService.getContactById(companyId, id);

    return contact != null ? ResponseEntity.ok(contactModelMapper.map(contact)) : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Get all contacts for a company", description = "Retrieves all contacts associated with a company")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved list of contacts", content = @Content(schema = @Schema(implementation = ContactModel.class)))
  @GetMapping(version = "1")
  public ResponseEntity<List<ContactModel>> getAllContacts(@PathVariable Integer companyId) {
    List<Contact> contacts = contactService.getContactsByCompanyId(companyId);

    return ResponseEntity.ok(contactModelMapper.map(contacts));
  }

  @Operation(summary = "Update a contact", description = "Updates an existing contact. The modification date is updated automatically.")
  @ApiResponse(responseCode = "200", description = "Contact updated successfully", content = @Content(schema = @Schema(implementation = ContactModel.class)))
  @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
  @ApiResponse(responseCode = "404", description = "Contact not found", content = @Content)
  @PutMapping(value = "/{id}", version = "1")
  public ResponseEntity<ContactModel> updateContact(@PathVariable Integer companyId, @PathVariable Integer id, @RequestBody CRUDContactModel crudContactModel) {
      Contact updatedContact = contactService.updateContact(companyId, id, contactModelMapper.map(crudContactModel));

      return ResponseEntity.ok(contactModelMapper.map(updatedContact));
  }

  @Operation(summary = "Delete a contact", description = "Deletes a contact from a company")
  @ApiResponse(responseCode = "204", description = "Contact deleted successfully", content = @Content)
  @ApiResponse(responseCode = "404", description = "Contact not found", content = @Content)
  @DeleteMapping(value = "/{id}", version = "1")
  public ResponseEntity<Void> deleteContact(@PathVariable Integer companyId, @PathVariable Integer id) {
      contactService.deleteContact(id);

      return ResponseEntity.noContent().build();
  }
}
