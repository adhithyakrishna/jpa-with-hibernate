package com.in28minutes.database.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.entity.Person;

//communicates and obtains values from the database

@Repository
public class PersonJdbcDao {

	// we will want spring to give the database connection to us
	@Autowired
	JdbcTemplate jdbctemplate;

	
	//creating a custom rowmapper
	class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Person person = new Person();
			
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			
			return person;
		}
		
	}
	
	// select * from person and return all the values of the person
	public List<Person> findAll() {
		/*
		 * the data returned would be of the form of result set, to map it to the person
		 * database, we make use of mapper since the data returned is in the same format
		 * as the person class, we'll use the default row mapper
		 */
		return jdbctemplate.query("select * from person", new BeanPropertyRowMapper(Person.class));
	}

	public Person findById(int id) {
		/*
		 * the data returned would be of the form of result set, to map it to the person
		 * database, we make use of mapper since the data returned is in the same format
		 * as the person class, we'll use the default row mapper
		 */
		return jdbctemplate.queryForObject("select * from person where id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public Person findByIdCustom(int id) {
		/*
		 * uses custom row mapper
		 */
		return jdbctemplate.queryForObject("select * from person where id = ?", new Object[] { id },
				new PersonRowMapper());
	}

	public int deleteById(int id) {
		return jdbctemplate.update("delete from person where id = ?", new Object[] { id });
	}

	public int insert(Person person) {
		return jdbctemplate.update("insert into person(id, name, location, birth_date) VALUES(?, ?, ?, ?)",
				new Object[] { person.getId(), person.getName(), person.getLocation(),
						new Timestamp(person.getBirthDate().getTime()) });
	}

	public int update(Person person) {
		return jdbctemplate.update("update person " + "set name = ?, location = ?, birth_date = ? " + "where id = ?",
				new Object[] { person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()),
						person.getId() });
	}
}
