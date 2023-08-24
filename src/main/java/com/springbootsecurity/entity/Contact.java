package com.springbootsecurity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Contact 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String number;
	private String firstname;
	private String lastname;
	
	public Contact() 
	{
		
	}
	
	public Contact(int id, String number, String firstname, String lastname) 
	{
		super();
		this.Id = id;
		this.number = number;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public int getId() 
	{
		return Id;
	}

	@Override
	public String toString() {
		return "Contact [Id=" + Id + ", number=" + number + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}

	public void setId(int id) 
	{
		this.Id = id;
	}

	public String getNumber() 
	{
		return number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getFirstname() 
	{
		return firstname;
	}

	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}

	public String getLastname() 
	{
		return lastname;
	}

	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}
	
	
	
	
	
}
