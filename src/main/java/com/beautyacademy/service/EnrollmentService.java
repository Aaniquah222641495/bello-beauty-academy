package com.beautyacademy.service;

import com.beautyacademy.model.Enrollment;
import com.beautyacademy.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Enrollment entities.
 * Provides business logic for CRUD operations on enrollments.
 */
@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    /**
     * Retrieves all enrollments from the database.
     *
     * @return a list of all Enrollment entities
     */
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    /**
     * Retrieves an enrollment by its unique identifier.
     *
     * @param id the ID of the enrollment to retrieve
     * @return an Optional containing the Enrollment if found, or empty if not found
     */
    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    /**
     * Creates a new enrollment in the database.
     *
     * @param enrollment the Enrollment entity to create
     * @return the saved Enrollment entity with generated ID
     */
    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    /**
     * Updates an existing enrollment's information.
     *
     * @param id the ID of the enrollment to update
     * @param enrollmentDetails the updated Enrollment details
     * @return the updated Enrollment entity if found, otherwise null
     */
    public Enrollment updateEnrollment(Long id, Enrollment enrollmentDetails) {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(id);
        if (optionalEnrollment.isPresent()) {
            Enrollment enrollment = optionalEnrollment.get();
            enrollment.setStudentId(enrollmentDetails.getStudentId());
            enrollment.setCourseId(enrollmentDetails.getCourseId());
            enrollment.setEnrollmentDate(enrollmentDetails.getEnrollmentDate());
            return enrollmentRepository.save(enrollment);
        }
        return null;
    }

    /**
     * Deletes an enrollment by its unique identifier.
     *
     * @param id the ID of the enrollment to delete
     * @return true if the enrollment was deleted, false if not found
     */
    public boolean deleteEnrollment(Long id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
