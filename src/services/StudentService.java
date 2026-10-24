package services;

import exceptions.StudentNotFoundException;
import models.Student;
import org.springframework.stereotype.Service;
import repositories.StudentRepository;

import java.util.List;

// handles all student-related business logic
// uses StudentRepository for persistence — no direct storage here
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // constructor injection — repository is passed in, not created here
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // register a new student — rejects duplicate email addresses
    public Student registerStudent(Student student) {
        if (student == null) throw new IllegalArgumentException("Student must not be null.");
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new IllegalArgumentException("A student with email " + student.getEmail() + " already exists.");
        }
        studentRepository.save(student);
        return student;
    }

    // find a student by ID — throws if not found
    public Student getStudentById(String studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }

    // return all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // return only students with at least one active enrollment
    public List<Student> getActiveStudents() {
        return studentRepository.findActiveStudents();
    }

    // update an existing student — throws if not found
    public Student updateStudent(String studentId, Student updatedStudent) {
        if (updatedStudent == null) throw new IllegalArgumentException("Updated student must not be null.");
        // confirm the student exists before updating
        getStudentById(studentId);
        studentRepository.save(updatedStudent);
        return updatedStudent;
    }

    // delete a student by ID — throws if not found
    public void deleteStudent(String studentId) {
        getStudentById(studentId);
        studentRepository.delete(studentId);
    }
}