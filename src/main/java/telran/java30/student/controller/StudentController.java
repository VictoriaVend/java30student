package telran.java30.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java30.student.dto.ScoreDto;
import telran.java30.student.dto.StudentDto;
import telran.java30.student.dto.StudentResponsDto;
import telran.java30.student.dto.StudentUpdateDto;
import telran.java30.student.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;
	@GetMapping("/students/name/{name}")
	public List<StudentResponsDto> findByName(@PathVariable String name){
		return studentService.fineByName(name);
	}

	@PostMapping("/student")
	public boolean addStudent(@RequestBody StudentDto studentDto) {
		return studentService.addStudent(studentDto);

	}

	@GetMapping("/student/{id}")
	public StudentResponsDto findStudent(@PathVariable Integer id) {
		return studentService.findStudenById(id);
	}

	@DeleteMapping("/student/{id}")
	public StudentResponsDto deleteStudent(@PathVariable Integer id) {
		return studentService.deleteStudent(id);

	}

	@PutMapping("/student/{id}")
	public StudentDto updateStudent(@PathVariable Integer id, @RequestBody StudentUpdateDto studentUpdateDto) {
		return studentService.updateStudent(id, studentUpdateDto);

	}

	@PutMapping("/score/student/{id}")
	public boolean addScoreToStudent(@PathVariable Integer id, @RequestBody ScoreDto scoreDto) {
		return studentService.addScore(id, scoreDto);

	}
	@GetMapping("/students/scors/{examName}/{minScore}")
	public List<StudentResponsDto>findByExamScore(@PathVariable String examName,@PathVariable int minScore){

		System.out.println(examName);
		return studentService.findStudentsByExamScore(examName, minScore);
	}

}
