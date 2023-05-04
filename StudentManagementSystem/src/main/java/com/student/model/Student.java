package com.student.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String state;
	
	@NotNull
	private String city;
	
	@NotNull
	private String standard;
	
	@Past
	private LocalDate DOB;
	
	@NotNull
	@NotBlank
	private String gender;
	
	@NotNull
	@Min(value = 0 , message = "Marks should be greater than or equal to 0")
    @Max(value = 100 , message = "Value should be less than or equal to 100")
	private Integer totalMarks;
	
	
	

}
