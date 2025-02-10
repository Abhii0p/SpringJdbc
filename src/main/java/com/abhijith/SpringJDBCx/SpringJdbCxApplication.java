package com.abhijith.SpringJDBCx;

import com.abhijith.SpringJDBCx.Repo.StudentRepository;
import com.abhijith.SpringJDBCx.model.Student;
import com.abhijith.SpringJDBCx.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbCxApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJdbCxApplication.class, args);

		StudentRepository repository = context.getBean(StudentRepository.class);

		// Test database connection
		repository.checkDatabaseConnection();

		// Save a student
		Student s = new Student();
		s.setRoll_no(21);
		s.setName("Anu");
		s.setMark(90);
		repository.save(s);

		// Retrieve all students
		List<Student> students = repository.findAll();
		students.forEach(System.out::println);
	}
}
