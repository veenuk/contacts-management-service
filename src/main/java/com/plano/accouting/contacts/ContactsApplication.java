package com.plano.accouting.contacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ContactsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}

}
