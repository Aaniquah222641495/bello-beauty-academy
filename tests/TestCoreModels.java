package tests;

import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestCoreModels {

    private Student student;
    private Course course;
    private Enrollment enrollment;

    @BeforeEach
    void setUp() {
        student    = new Student("STU-001", "Amira Patel", "amira@example.com", "hash123", "+27821234567");
        course     = new Course("CRS-001", "Classic Lash Extensions Course",
                "Foundational lash techniques.", CourseCategory.LASH, 3, 2500.00);
        enrollment = new Enrollment("ENR-001", student, course);
    }

    // student tests

    @Test
    void studentIsActiveByDefault() {
        assertTrue(student.isActive());
    }

    @Test
    void studentRoleIsCorrect() {
        assertEquals(UserRole.STUDENT, student.getRole());
    }

    @Test
    void studentActiveEnrollmentCountUpdates() {
        enrollment.activate();
        student.addEnrollment(enrollment);
        assertEquals(1, student.getActiveEnrollmentCount());
    }

    @Test
    void addingNullEnrollmentThrows() {
        assertThrows(IllegalArgumentException.class, () -> student.addEnrollment(null));
    }

    @Test
    void enrollmentsListIsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class,
                () -> student.getEnrollments().add(enrollment));
    }

    // course tests

    @Test
    void courseIsActiveByDefault() {
        assertFalse(course.isActive()); // temporarily broken for CI demo
    }
    @Test
    void courseAttributesAreSetCorrectly() {
        assertEquals("CRS-001", course.getCourseId());
        assertEquals("Classic Lash Extensions Course", course.getTitle());
        assertEquals(CourseCategory.LASH, course.getCategory());
        assertEquals(3, course.getDurationDays());
        assertEquals(2500.00, course.getPrice(), 0.001);
    }

    @Test
    void courseMaterialIsAdded() {
        course.addMaterial("/uploads/lash-manual.pdf");
        assertEquals(1, course.getMaterials().size());
    }

    @Test
    void negativePriceThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("X", "Title", "Desc", CourseCategory.NAIL, 1, -10.0));
    }

    @Test
    void zeroDurationThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("X", "Title", "Desc", CourseCategory.BROW, 0, 100.0));
    }

    // enrollment tests

    @Test
    void newEnrollmentIsPending() {
        assertEquals(EnrollmentStatus.PENDING, enrollment.getStatus());
    }

    @Test
    void enrollmentActivatesFromPending() {
        enrollment.activate();
        assertEquals(EnrollmentStatus.ACTIVE, enrollment.getStatus());
    }

    @Test
    void enrollmentCompletesFromActive() {
        enrollment.activate();
        enrollment.complete();
        assertEquals(EnrollmentStatus.COMPLETED, enrollment.getStatus());
    }

    @Test
    void activatingActiveEnrollmentThrows() {
        enrollment.activate();
        assertThrows(IllegalStateException.class, enrollment::activate);
    }

    @Test
    void completingPendingEnrollmentThrows() {
        assertThrows(IllegalStateException.class, enrollment::complete);
    }

    @Test
    void enrollingInInactiveCourseThrows() {
        course.setActive(false);
        assertThrows(IllegalStateException.class,
                () -> new Enrollment("ENR-002", student, course));
    }

    // certificate tests

    @Test
    void certificateAttributesAreSetCorrectly() {
        Certificate cert = new Certificate("CERT-001", student, course,
                "BBA-LASH-2026-00142", "/certs/amira-lash.pdf");
        assertEquals("CERT-001", cert.getCertificateId());
        assertEquals("BBA-LASH-2026-00142", cert.getCertificateNumber());
        assertNotNull(cert.getIssueDate());
    }

    @Test
    void certificateWithNullStudentThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Certificate("C-001", null, course, "NO", "/path"));
    }
}