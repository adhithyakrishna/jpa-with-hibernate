package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;

	@Autowired
	EntityManager em;

	// Session & Session Factory

	// EntityManager & Persistence Context
	// Transaction

	@Test
	public void someTest() {
		/*
		 * This method is important to understnand if we perform some method that alters
		 * the database here we have to include @transactional at the top of the
		 * function if we are using entity manager to perform a function, if we are
		 * using a method within a student repository and if that class has
		 * an @transactional tag, we dont have to put @transactional at the top of the
		 * method
		 */

		repository.someOperationToUnderstandPersistenceContext();
	}

	/*
	 * The transactional annotation at the top of the function makes sure that the
	 * fields within the entity classes, if marked lazy are properly retrieved Video
	 * number 72 explains it
	 * 
	 * transactional creates the persistence context it enables persistence context
	 * and rolls back if there are any failure in updates
	 */
	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);

		/*
		 * Any one to one relationship is an eager fetch, you would fetch, i.e passport
		 * details are fetched since student owns passport, so it fetches data from
		 * passport as well even if not explicitly asked for
		 * 
		 * Transactional creates a persistence context which will keep track of all the
		 * entities that are either intialised or used within the transactional
		 * functional block in hibernate terminology session = persistence context
		 */
		logger.info("passport -> {}", student.getPassport());
	}
}
