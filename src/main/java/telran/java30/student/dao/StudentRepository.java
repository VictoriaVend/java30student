package telran.java30.student.dao;

import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.java30.student.model.Student;

public interface StudentRepository extends MongoRepository<Student, Integer> {
	Stream<Student> findByName(String name);

	Stream<Student> findByNameRegex(String name);

	long countByName(String name);

	@Query("{'?0':{'$gte:?1'}}")
	Stream<Student> findByScors(String examName, int minScore);

}
