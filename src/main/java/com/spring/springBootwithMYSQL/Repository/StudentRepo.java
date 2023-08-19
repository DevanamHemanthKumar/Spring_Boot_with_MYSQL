package com.spring.springBootwithMYSQL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.springBootwithMYSQL.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
