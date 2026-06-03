package com.beautyacademy.service;

import com.beautyacademy.model.Course;
import com.beautyacademy.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Course entities.
 * Provides business logic for CRUD operations on courses.
 */
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Retrieves all courses from the database.
     *
     * @return a list of all Course entities
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /**
     * Retrieves a course by its unique identifier.
     *
     * @param id the ID of the course to retrieve
     * @return an Optional containing the Course if found, or empty if not found
     */
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    /**
     * Creates a new course in the database.
     *
     * @param course the Course entity to create
     * @return the saved Course entity with generated ID
     */
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * Updates an existing course's information.
     *
     * @param id the ID of the course to update
     * @param courseDetails the updated Course details
     * @return the updated Course entity if found, otherwise null
     */
    public Course updateCourse(Long id, Course courseDetails) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setName(courseDetails.getName());
            course.setDescription(courseDetails.getDescription());
            course.setCredits(courseDetails.getCredits());
            return courseRepository.save(course);
        }
        return null;
    }

    /**
     * Deletes a course by its unique identifier.
     *
     * @param id the ID of the course to delete
     * @return true if the course was deleted, false if not found
     */
    public boolean deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
