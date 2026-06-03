package com.beautyacademy.service;

import com.beautyacademy.model.Student;
import com.beautyacademy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Student entities.
 * Provides business logic for CRUD operations on students.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Retrieves all students from the database.
     *
     * @return a list of all Student entities
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Retrieves a student by their unique identifier.
     *
     * @param id the ID of the student to retrieve
     * @return an Optional containing the Student if found, or empty if not found
     */
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    /**
     * Creates a new student in the database.
     *
     * @param student the Student entity to create
     * @return the saved Student entity with generated ID
     */
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Updates an existing student's information.
     *
     * @param id the ID of the student to update
     * @param studentDetails the updated Student details
     * @return the updated Student entity if found, otherwise null
     */
    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(studentDetails.getName());
            student.setEmail(studentDetails.getEmail());
            student.setPhone(studentDetails.getPhone());
            return studentRepository.save(student);
        }
        return null;
    }

    /**
     * Deletes a student by their unique identifier.
     *
     * @param id the ID of the student to delete
     * @return true if the student was deleted, false if not found
     */
    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
