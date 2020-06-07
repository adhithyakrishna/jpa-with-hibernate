package com.in28minutes.database.databasedemo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "find_all_persons", query="select p from Person p")
@Table(name = "Person") // This is a table that it maps to, table name matches to the name of the class
public class Person {

	@Id // primary key
	@GeneratedValue // creates a sequence in db and uses it to populate
	private int id;

	@Column(name = "name") // if you want to create a custom column
	private String name;
	private String location;
	private Date birthDate;

	public Person(int id, String name, String location, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}

	/*
	 * This constructor is to support the hibernate implementation of the automatic
	 * row generation, we dont need to pass in the id, because that would be taken
	 * care by the hibernate @ID annotation, person can be created without an id
	 * being passed
	 */
	public Person(String name, String location, Date birthDate) {
		super();
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}

	/*
	 * when ever a bean property row mapper is used, the bean on which it is used
	 * should have an no argument constructor
	 */
	public Person() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", location=" + location + ", birthDate=" + birthDate + "]";
	}

}
