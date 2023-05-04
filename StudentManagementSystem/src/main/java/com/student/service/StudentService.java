package com.student.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.student.model.Student;



public interface StudentService {

	public List<Student> getAllStudents();
	public void saveStudent(Student student);
	public Student getStudentById(long id);
	public void deleteStudentById(long id);
	public Page <Student> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection);
}

