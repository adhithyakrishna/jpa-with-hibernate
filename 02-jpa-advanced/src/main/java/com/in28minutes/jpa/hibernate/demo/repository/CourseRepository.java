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

	@Autowired
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	// insert as well as update an existing course
	public Course save(Course course)
	{
		 
		if(course.getId() == null)
		{
			//create a new course
			em.persist(course);
		}
		else {
			//update the course
			em.merge(course);
		}
		return course;
	}

	/*
	 * if we are just querying a data, we dont need to include transaction but since
	 * we are trying to modify a data by deleting it we would require transaction
	 * we want an operation to either complete fully or exit fully
	 */
	public void deleteById(Long id) {
		Course courseTodelete = findById(id);
		em.remove(courseTodelete);
	}

}