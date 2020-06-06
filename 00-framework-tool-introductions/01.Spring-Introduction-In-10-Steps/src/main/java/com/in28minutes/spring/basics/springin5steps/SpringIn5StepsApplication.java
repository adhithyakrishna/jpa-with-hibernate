package com.in28minutes.spring.basics.springin5steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringIn5StepsApplication {

	// What are the beans?
	// What are the dependencies of a bean?
	// Where to search for beans? => No need

	public static void main(String[] args) {

		//beans can be retrieved from the application context
		//bean can also be obtained using name and casted
		ApplicationContext applicationContext = 
				SpringApplication.run(SpringIn5StepsApplication.class, args);
		
		
		BinarySearchImpl binarySearch = 
				applicationContext.getBean(BinarySearchImpl.class);
		int result = 
				binarySearch.binarySearch(new int[] { 12, 4, 6 }, 3);
		System.out.println(result);
	}
}

/* 
Binary search has to sort before it searches for the required value
Sorting can be done either throught quicksort, bubblesort etc
instead of manually changing the code, the code can be re written
as a loosely coupled code using interface, the dependencies can be
autowired using autowired annotation

Autowiring can be done either by constructor, setter, property
if there are two components, pertaining to the same interface,
priority can be given using @primary inteface.
*/