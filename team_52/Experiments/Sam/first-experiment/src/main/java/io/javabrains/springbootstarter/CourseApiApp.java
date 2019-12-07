package io.javabrains.springbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApiApp {
	
	public static void main(String[] args)
	{
		//have to find way that this main method is a spring 
		//application (Annotation: SpringBootApplication)
		
		//need to create this main method to be run within the 
		//SpringBoot container(specified by @SpringBootAnnotation)
		//do this with
		SpringApplication.run(CourseApiApp.class, args);
		//running the springbootapplication by running the 
		//@SpringBootApplication class
		
		
	}

}
