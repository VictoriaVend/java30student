package telran.java30.student.service;

import java.util.List;

import telran.java30.student.dto.ScoreDto;
import telran.java30.student.dto.StudentDto;
import telran.java30.student.dto.StudentResponsDto;
import telran.java30.student.dto.StudentUpdateDto;

public interface StudentService {
	boolean addStudent(StudentDto studentDto);

	StudentResponsDto findStudenById(Integer id);

	StudentResponsDto deleteStudent(Integer id);

	StudentDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto);

	boolean addScore(Integer id, ScoreDto scoreDto);

	List<StudentResponsDto> fineByName(String name);

	long countStudentByName(String name);

	
	
	
	List<StudentResponsDto> findStudentsByExamScore(String examName, int minScore);

}
