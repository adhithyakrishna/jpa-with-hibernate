package com.in28minutes.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

//To use multiple queries, we should make use of named queries annotation
@NamedQueries(value = { @NamedQuery(name = "query_get_all_courses", query = "Select c from Course c"),
		@NamedQuery(name = "query_get_100_step_courses", query = "Select c from Course c where name like '%100'") })
//NamedQuery can be used only once

@Entity
/*
 * when your underlying database has the table name as coursedetails but you
 * want the entity to remain as course, we can use @Table entity to display the
 * table name, after this we dont have to specify it anywhere in the test as
 * well as our repository it will get automatically mapped
 */
//@Table(name = "CourseDetails")
public class Course {

	protected Course() {

	}

	public Course(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	private Long id;

	/*
	 * @column - The column in the table name will be converted to fullname
	 * 
	 * @nullable - The value in the name cannot be assigned a value of null
	 */
	@Column(nullable = false)
	private String name;

	/*
	 * JPA expects no arg constructor, mandated by jpa protect because, Other
	 * classes that do not inherit from course will not make use of the constructor
	 */

	/*
	 * Everytime a change is made to a row, the @updateTimestamp updates the row to
	 * the last updated time, we can say that using an annotation. it is important
	 * to note that it is an hibernate annotation and if you tend to change your jpa
	 * implementation, you wont have support for this
	 */

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	/*
	 * When the row is first created, this is inserted
	 */
	
	@CreationTimestamp
	private LocalDateTime createdDate;

	/*
	 * whenever a collection is used, it is advisable to initialise it there itself
	 * We'll have to look at the relationship annotations on the basis of the entity
	 * name the name of the entity is course, and the name of the filed is reviews.
	 * "one" course can have "many" reviews and hence one to many relationship
	 * 
	 * by default onetomany relationship is lazy, fetch = FetchType.EAGER
	 */

	@OneToMany(mappedBy="course")
	private List<Review> reviews = new ArrayList<Review>();

	
	/*
	 * mapped by is added to prevent duplication of joins table 
	 * now Students is the owning side of the many to many relationship
	 */
	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<>(); 
	
	public List<Student> getStudents() {
		return students;
	}

	public void addStudents(Student student) {
		this.students.add(student);
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

}