package com.springbootsecurity.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.springbootsecurity.entity.Contact;
import com.springbootsecurity.repository.ContactRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class HomeController 
{
	@Autowired
	private ContactRepository contactRepository;
	@GetMapping("/")
	public String index()
	{
		return "Contact Details";
	}
	
	@PostMapping("/saveContact")
	public ResponseEntity<String> saveData(@RequestBody Contact contact) {
	    Optional<Contact> existingContact = contactRepository.findByPhoneNumber(contact.getNumber());

	    if (existingContact.isPresent()) {
	        return ResponseEntity.badRequest().body("Contact with this phone number already exists.");
	    } else {
	        contactRepository.save(contact);
	        return ResponseEntity.ok("Contact saved successfully.");
	    }
	}

	
	@GetMapping("/getAllContacts")
	public List<Contact> getAll()
	{
		List<Contact> contactList= contactRepository.findAll();
		return contactList;
	}
	
	@DeleteMapping("/deleteContact/{Id}")
	public String deleteContact(@PathVariable int Id)
	{
		Contact contact= contactRepository.findById(Id).get();
		if(contact != null)
			contactRepository.delete(contact);
		return "Deleted Successfully";
	}
	
	@GetMapping("/getContact/{number}")
	public ResponseEntity<Contact> getContact(@PathVariable String number) {
	    Optional<Contact> contact = contactRepository.findByPhoneNumber(number);

	    if (contact.isPresent()) {
	        return ResponseEntity.ok(contact.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/getContactByName/{firstname}")
	public ResponseEntity<Contact> getContactByName(@PathVariable String firstname) {
	    Optional<Contact> contact = contactRepository.findByName(firstname);

	    if (contact.isPresent()) {
	        return ResponseEntity.ok(contact.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@PutMapping("/updateContact")
	public Contact updateContact(@RequestBody Contact contact)
	{
		contactRepository.save(contact);
		return contact;
		
	}
	
	@GetMapping("/exportContactsToCSV")
	public void exportContactsToCSV(HttpServletResponse response) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
	    List<Contact> contacts = contactRepository.findAll();

	    String filePath = "E:/CSV/contacts.csv";

	    response.setContentType("text/csv");
	    response.setHeader("Content-Disposition", "attachment; filename=\"contacts.csv\"");

	    FileWriter fileWriter = new FileWriter(filePath);
	    CsvWriterUtility<Contact> csvWriter = new CsvWriterUtility<>(fileWriter, Contact.class);

	    String[] csvHeader = { "firstname", "lastname","number"};
	    csvWriter.writeHeader(csvHeader);

	    csvWriter.writeAll(contacts, csvHeader);

	    fileWriter.close();
	}


}
