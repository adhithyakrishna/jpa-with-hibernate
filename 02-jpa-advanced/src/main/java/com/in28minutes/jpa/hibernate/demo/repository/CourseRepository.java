package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

//Repository is used to communicate with entity manager class
@Repository
//Transaction makes sure that the execution runs completely or fails totally
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	// insert as well as update an existing course
	public Course save(Course course) {

		if (course.getId() == null) {
			// create a new course
			em.persist(course);
		} else {
			// update the course
			em.merge(course);
		}
		return course;
	}

	/*
	 * if we are just querying a data, we dont need to include transaction but since
	 * we are trying to modify a data by deleting it we would require transaction we
	 * want an operation to either complete fully or exit fully
	 */
	public void deleteById(Long id) {
		Course courseTodelete = findById(id);
		em.remove(courseTodelete);
	}

	public void playWithEntityManager() {
		logger.info("Play with entity manager start");
		
		//em.persist - create a new course, create a new row into the database
		
		Course course = new Course("Welcome to this world");
		em.persist(course);
		/* The below code will get updated new value to the above inserted row
		 * This is being done by the @Transactional - so entity manager keeps track of whatever 
		 * was modified, whenever there is a change it makes sure that, that data is persisted to the database
		 * this happens until the end of the transaction
		 */
		
		/*
		 * we use flush to send whatever we have done so far to the database i.e the transaction is complete
		 * up until that point - Synchronizes to the persistence context to the underlying database
		 */
		em.flush();
		
		course.setName("Welcome to this world - Again");
		
		/*
		 * Refresh the entity, that is if we make any update, and we want to reset it to the original value
		 *  we can make use of refresh
		 */
		
		em.refresh(course);
		
		
		/*
		 * The course object would no longer be tracked by the entity manager if we make use of detach
		 * any changes we make after that would not be synchronised with the database, all the unflushed data
		 * will not be reflected
		 */
		em.detach(course);
		
		/*
		 * To detach all the entities being tracked use clear
		 * 
		 */
		em.clear();
	}
}