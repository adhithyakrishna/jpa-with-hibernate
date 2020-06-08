package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;

/*The runwith annotation launches spring context*/
@RunWith(SpringRunner.class)
//launch the test on springboot application
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	@Autowired
	EntityManager em;

	@Test
	public void findById_basic() {
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
		// assert equals left side is the expected value, and the right side is the
		// value obtained
	}

	/*
	 * Because this test is modifying the state of the database, we should make sure
	 * that the data should be restored so we use DirtiesContext
	 */
	@Test
	@DirtiesContext
	public void deleteById_basic() {
		repository.deleteById(10002l);
		assertNull(repository.findById(10002l));
	}

	@Test
	@DirtiesContext
	public void saveCourse() {
		Course course = repository.findById(10001l);
		assertEquals("JPA in 50 Steps", course.getName());

		course.setName("JPA in 50 Steps - new");
		repository.save(course);

		Course updatedCourse = repository.findById(10001l);
		assertEquals("JPA in 50 Steps - new", updatedCourse.getName());
	}

	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}

	@Test
	@Transactional
	public void retrieveReviewsForCourse() {

		Course course = repository.findById(10001l);
		logger.info("{}", course.getReviews());
	}

	@Test
	public void retrieveCourseForReview() {

		Review review = em.find(Review.class, 50001l);
		logger.info("sing in {}", review.getCourse());		
	}
}
