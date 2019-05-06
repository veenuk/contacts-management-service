package com.plano.accouting.contacts;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ContactsResource {

	@Autowired
	private ContactsMockDataService contactService;

	@GetMapping("/contacts/all")
	public List<Contact> getAllContacts() {
		// Thread.sleep(3000);
		return contactService.findAll();
	}
	
	@GetMapping("/contacts/{id}")
	public Contact getContact(@PathVariable long id) {
		// Thread.sleep(3000);
		return contactService.findById(id);
	}

	// DELETE /contacts/{id}
	@DeleteMapping("/contacts/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable long id) {

		Contact contact = contactService.deleteById(id);

		if (contact != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	
	//Edit/Update a Contact
	//PUT /contacts/{contact_id}
	@PutMapping("/contacts/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable long id, @RequestBody Contact contact){
		
		Contact contactUpdated = contactService.save(contact);
		
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}
	
	@PostMapping("/contacts")
	public ResponseEntity<Void> updateContact(@RequestBody Contact contact){
		
		Contact createdContact = contactService.save(contact);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdContact.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		
}