package com.spring.springBootwithMYSQL.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springBootwithMYSQL.Repository.StudentRepo;
import com.spring.springBootwithMYSQL.model.Student;

@RestController
public class StudentController {
	@Autowired
	StudentRepo studentRepo;
	
	@PostMapping("/student")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		return new ResponseEntity<>(studentRepo.save(student),HttpStatus.CREATED);
	}

	@GetMapping("/student")
	public ResponseEntity<List<Student>> getStudents(){
		return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
	}
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable int id){
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<>(student.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/student/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestBody Student stud){
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			student.get().setStudentName(stud.getStudentName());
			student.get().setStudentEmail(stud.getStudentEmail());
			student.get().setStudentAddress(stud.getStudentAddress());
			return new ResponseEntity<Student>(studentRepo.save(student.get()),HttpStatus.OK);
		}else {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/student/{id})")
	public ResponseEntity<Student> deleteStudent(@PathVariable int id){
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			studentRepo.deleteById(id);
			return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
}
