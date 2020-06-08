package com.in28minutes.jpa.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	/*
	 * Student table is owning the relationship because passport is handled in
	 * student
	 */

	/*
	 * To perform a lazy fetch of passport we should need to have a fetch type as
	 * lazy, the query will retrieve only the student data even though student has
	 * passport as a getter, that will not be included
	 */

	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;

	@ManyToMany
	@JoinTable (
		name = "STUDENT_COURSE", 
		joinColumns = @JoinColumn(name = "STUDENT_ID"), 
		inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
	)
	private List<Course> courses = new ArrayList<>();

	public List<Course> getCourses() {
		return courses;
	}

	public void addCourses(Course courses) {
		this.courses.add(courses);
	}

	protected Student() {
	}

	public Student(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Student[%s]", name);
	}
}