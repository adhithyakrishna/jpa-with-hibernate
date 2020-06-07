package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void findById_basic() {
		List resultList = em.createNamedQuery("query_get_all_courses").getResultList();
		logger.info(" The result list for find by id basic is -> {}", resultList);
	}

	@Test
	public void findById_typed() {
		/*
		 * when you expect results of the type course use can use typed query and pass
		 * the class as a parameter
		 */
		TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info(" The result list for find by id basic is -> {}", resultList);
	}

	@Test
	public void where_basic() {
		/*
		 * when you expect results of the type course use can use typed query and pass
		 * the class as a parameter
		 */
		TypedQuery<Course> query = em.createNamedQuery("query_get_100_step_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info(" The result list for find by id basic is -> {}", resultList);
	}

}

/*
 * Writing query in a seperate class would make it difficult to reuse the code
 * So it is better to use named query, which would make it easy to access it
 * anywhere the entity is used
 */
