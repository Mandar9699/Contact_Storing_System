package com.springbootsecurity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springbootsecurity.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>
{
	@Query(value = "SELECT * FROM contact WHERE number = :number", nativeQuery = true)
	Optional<Contact> findByPhoneNumber(String number);

	@Query(value = "SELECT * FROM contact WHERE firstname = :firstname", nativeQuery = true)
	Optional<Contact> findByName(String firstname);
	
}
