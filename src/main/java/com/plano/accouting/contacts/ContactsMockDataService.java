package com.plano.accouting.contacts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ContactsMockDataService {

	private static List<Contact> contacts = new ArrayList<>();
	private static long idCounter = 0;

	static {
		contacts.add(new Contact(++idCounter, "3d543fb5-90dc-4f2a-8a37-ebb55de76d1c","Some", "User", "Some address","111-222-5555", "some.user@gmail.com"));
	}

	public List<Contact> findAll() {
		return contacts;
	}
	
	public Contact save(Contact contact) {
		if(contact.getId()==-1 || contact.getId()==0) {
			contact.setId(++idCounter);
			contacts.add(contact);
		} else {
			deleteById(contact.getId());
			contacts.add(contact);
		}
		return contact;
	}

	public Contact deleteById(long id) {
		Contact contact = findById(id);

		if (contact == null)
			return null;

		if (contacts.remove(contact)) {
			return contact;
		}

		return null;
	}

	public Contact findById(long id) {
		for (Contact contact : contacts) {
			if (contact.getId() == id) {
				return contact;
			}
		}

		return null;
	}
}