package com.in28minutes.database.databasedemo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.entity.Person;

/*
 * This class is a repository and it does transaction management
 * it either does something completely or it should fail completely
 * ideally transcation is done around services
 */

@Repository
@Transactional
public class PersonJpaRepository {

	// connecting to the database
	/*
	 * Entity manager manages entitites, and session data Entity manager is the
	 * interface to the persistance context all operations would be going through
	 * the entity context
	 */

	@PersistenceContext
	EntityManager entityManager;

	public Person findById(int id) {
		return entityManager.find(Person.class, id);
	}

	/*
	 * If you want to update or insert a person you can make use of merge if there
	 * is no id parameter set, then it knows that we are trying to insert a new
	 * person if there is an id parameter set, then it know that we are just trying
	 * to update that person
	 */
	public Person update(Person person) {
		return entityManager.merge(person);
	}
	
	
	public Person insert(Person person) {
		return entityManager.merge(person);
	}

	
	/*
	 *  delete works by first finding the person and then deleting it
	 */
	
	public void delete(int id)
	{
		Person toDelete = findById(id);
		entityManager.remove(toDelete);
	}
	 
	
	/*
	 * there is no default functionality for findall, so we write JPQL, a java
	 * persistence query language using a concept called named query
	 * named query is defined in the entity class
	 */
	
	
	public List<Person> findAll() 
	{
		TypedQuery<Person> namedQuery  = entityManager.createNamedQuery("find_all_persons", Person.class);
		return namedQuery.getResultList();
	}
}	 
