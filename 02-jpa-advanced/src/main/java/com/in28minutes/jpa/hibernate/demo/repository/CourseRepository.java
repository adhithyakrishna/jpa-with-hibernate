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
public class CourseRepository {

	@Autowired
	EntityManager em;
	
	public Course findById(Long id)
	{
		return em.find(Course.class, id);
	}
	
	//insert as well as update an existing course
//	public Course save(Course course)
	
	
//	public void deleteById(Long id)
	
}