package com.plano.accouting.contacts;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ContactsJpaResource {
	
	@Autowired
	private ContactsMockDataService contactsService;

	@Autowired
	private ContactsJpaRepository contactsJpaRepository;

	
	@GetMapping("/jpa/contacts")
	public List<Contact> getAllContacts(){

		return contactsService.findAll();
	}

	@GetMapping("/jpa/contacts/{id}")
	public Contact getContact( @PathVariable long id){
		
		return contactsService.findById(id);
	}

	// DELETE /contacts/{id}
	@DeleteMapping("/jpa/contacts/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable long id) {

		contactsJpaRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	

	//Edit/Update a Contact
	//PUT /users/{user_name}/contacts/{contact_id}
	@PutMapping("/jpa/contacts/{id}")
	public ResponseEntity<Contact> updateContact(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Contact contact){
		
		Contact contactUpdated = contactsJpaRepository.save(contact);
		
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/contacts")
	public ResponseEntity<Void> createContact(
			@PathVariable String username, @RequestBody Contact contact){
		
		Contact createdContact = contactsJpaRepository.save(contact);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdContact.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		
}
