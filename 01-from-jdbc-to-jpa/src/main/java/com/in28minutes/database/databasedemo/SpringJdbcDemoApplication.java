package com.in28minutes.database.databasedemo;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.in28minutes.database.databasedemo.entity.Person;
import com.in28minutes.database.databasedemo.jdbc.PersonJdbcDao;

@SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {

	//To fire a query, we would be needing a person jdbc dao
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonJdbcDao personJdbcDao;
	
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	/*
	 * This method is from commandLineRunner, the purpose is to let the contents
	 * inside the function run as soon as soon as the application starts
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", personJdbcDao.findAll());
		logger.info("All users -> {}", personJdbcDao.findById(10001));
		
		logger.info("Delete -> {}", personJdbcDao.deleteById(10002));
		
		Person impPerson = new Person(10004, "Adhithya", "Coimbatore", new Date());
		logger.info("insert new user ->{}", personJdbcDao.insert(impPerson));
		
		Person secondimpPerson = new Person(10004, "Krishna", "Coimbatore", new Date());
		logger.info("update existing user ->{}", personJdbcDao.update(secondimpPerson));
		
		logger.info("All users obtained -> {}", personJdbcDao.findByIdCustom(10004));
		
	}
}