package telran.java30.student.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java30.student.dao.StudentRepository;
import telran.java30.student.dto.ScoreDto;
import telran.java30.student.dto.StudentDto;
import telran.java30.student.dto.StudentNotFoundExeption;
import telran.java30.student.dto.StudentResponsDto;
import telran.java30.student.dto.StudentUpdateDto;
import telran.java30.student.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository studentRepository;

	@Override
	public boolean addStudent(StudentDto studentDto) {
		if (studentRepository.existsById(studentDto.getId())) {
			return false;
		}
		Student student = new Student(studentDto.getId(), studentDto.getName(), studentDto.getPassword());
		studentRepository.save(student);
		return true;

	}

	@Override
	public StudentResponsDto findStudenById(Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundExeption(id));

		return studentToStudentResponseDto(student);

		// Student student = studentRepository.findStudent(id);
		// if (student == null) {
		// throw new StudentNotFoundExeption(id);
		// }
		// Student student2= new Student();
		// student.getScores().forEach((k,v)-> student2.addScore(k,v));
		// return
		// StudentResponsDto.builder().id(id).name(student.getName()).scores(student.getScores()).build();

	}

	@Override
	public StudentResponsDto deleteStudent(Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundExeption(id));
		studentRepository.deleteById(id);

		return studentToStudentResponseDto(student);

		// Student student = studentRepository.removeStuden(id);
		// return
		// StudentResponsDto.builder().id(id).name(student.getName()).scores(student.getScores()).build();

	}

	@Override
	public StudentDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundExeption(id));
		student.setName(studentUpdateDto.getName());
		student.setPassword(studentUpdateDto.getPassword());
		studentRepository.save(student);
		return studentToStudentDto(student);

		/*
		 * Student student = studentRepository.findStudent(id);
		 * student.setName(studentUpdateDto.getName());
		 * student.setPassword(studentUpdateDto.getPassword()); return
		 * StudentDto.builder().id(student.getId()).name(student.getName()).password(
		 * student.getPassword()).build();
		 */

	}

	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundExeption(id));
		boolean b = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return b;

		/*
		 * Student student = studentRepository.findStudent(id); if (student == null) {
		 * throw new StudentNotFoundExeption(id); } return
		 * student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		 */

	}

	private StudentDto studentToStudentDto(Student student) {
		return StudentDto.builder().id(student.getId()).name(student.getName()).password(student.getPassword()).build();
	}

	private StudentResponsDto studentToStudentResponseDto(Student student) {
		return StudentResponsDto.builder().id(student.getId()).name(student.getName()).scores(student.getScores())
				.build();
	}

	@Override
	public List<StudentResponsDto> fineByName(String name) {

		return studentRepository.findByName(name).map(s -> studentToStudentResponseDto(s))
				// .map(this::studentToStudentResponseDto)
				.collect(Collectors.toList());
	}

	@Override
	public long countStudentByName(String name) {
	
		return studentRepository.countByName(name);
	}

	@Override
	public List<StudentResponsDto> findStudentsByExamScore(String examName, int minScore) {
		
		return studentRepository.findByScors("scors."+examName,minScore)
				.map(this::studentToStudentResponseDto).collect(Collectors.toList());
	}
}
