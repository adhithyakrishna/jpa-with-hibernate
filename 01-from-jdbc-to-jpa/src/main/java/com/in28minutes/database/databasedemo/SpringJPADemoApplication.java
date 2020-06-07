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
import com.in28minutes.database.databasedemo.jpa.PersonJpaRepository;

@SpringBootApplication
public class SpringJPADemoApplication implements CommandLineRunner {

	// To fire a query, we would be needing a person jdbc dao

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonJpaRepository personJdbcRepo;

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringJPADemoApplication.class, args);
	}

	/*
	 * This method is from commandLineRunner, the purpose is to let the contents
	 * inside the function run as soon as soon as the application starts
	 */
	@Override
	public void run(String... args) throws Exception {

		Person impPerson = new Person("Adhithya", "Coimbatore", new Date());
		logger.info("insert new user ->{}", personJdbcRepo.insert(impPerson));

		Person secondimpPerson = new Person(1, "Krishna", "Coimbatore", new Date());
		logger.info("update existing user ->{}", personJdbcRepo.update(secondimpPerson));

		logger.info("Find a particular -> {}", personJdbcRepo.findById(1));

		logger.info("All users -> {}", personJdbcRepo.findAll());

		personJdbcRepo.delete(1);

		/*
		 * logger.info("All users obtained -> {}",
		 * personJdbcRepo.findByIdCustom(10004));
		 */
	}
}

/*
 * Using hibernate triggers the schema update, when we are using an in memory
 * database which looks at the entities and create a schema for us, so we remove
 * the schema creation from the mysql database
 */
