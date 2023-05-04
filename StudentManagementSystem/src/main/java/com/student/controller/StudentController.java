package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.student.model.Student;
import com.student.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "name", "asc", model);		
	}
	
	@GetMapping("/showNewStudentForm")
	public String showNewEmployeeForm(Model model) {
		
		try {
			Student student = new Student();
			model.addAttribute("student", student);
			return "new_student";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student) {
		try {
	        studentService.saveStudent(student);
	        return "redirect:/";
        } catch (Exception e) {
	        e.printStackTrace();
	        return null;
         }
		
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		try {
			Student student = studentService.getStudentById(id);
			System.out.println(student.getDOB());
			// set employee as a model attribute to pre-populate the form
			model.addAttribute("student", student);
			return "update_student";
		} catch (Exception e) {
	        e.printStackTrace();
	        return null;
         }
		
		
	
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable (value = "id") long id) {
		try {
			this.studentService.deleteStudentById(id);
			return "redirect:/";
		}
		catch (Exception e) {
			 e.printStackTrace();
		        return null;
		}
		
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Student> listStudents = page.getContent();
		for(Student student : listStudents) {
			System.out.println(student.getDOB()+"    <------>   " +student.getTotalMarks());
		}
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listStudents", listStudents);
		return "index";
	}
}
