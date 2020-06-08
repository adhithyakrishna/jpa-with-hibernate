package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String number;

	/*
	 * Mapped by is used here because when we tried to make the one to one
	 * relationship bidirectional i.e when we wanted to access student data from
	 * passport as well as access passport data from student the database created
	 * duplicate information, that is student_id was created in passport and
	 * passpord_id was created in student, to prevent this we use mapped by
	 * property. this ensures that the data is stored only in the owning table, that
	 * is the information is now stored only in student because we are using mapped
	 * by fieldname i.e "passport" above the entity we are trying to fetch
	 * 
	 * mapped by is added on the non owning side of the relationship
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
	private Student student;

	protected Passport() {
	}

	public Passport(String number) {
		this.number = number;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Passport[%s]", number);
	}
}