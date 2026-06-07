package api;

import exceptions.*;
import models.*;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import services.*;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TestApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private CourseService courseService;

    @MockBean
    private EnrollmentService enrollmentService;

    private Student student;
    private Course course;
    private Enrollment enrollment;

    @BeforeEach
    void setup() {
        student    = new Student("S001", "Aaliyah Adams", "aaliyah@email.com", "hashed123", "0821234567");
        course     = new Course("C001", "Classic Lash Extensions", "desc", CourseCategory.LASH, 3, 1500.00);
        enrollment = new Enrollment("E001", student, course);
    }

    // ─── Student endpoints ───────────────────────────────────────────────────

    // GET /api/students — should return 200 with a list of students
    @Test
    @Order(1)
    void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(List.of(student));
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value("S001"));
    }

    // GET /api/students/{id} — should return 200 for a known student
    @Test
    @Order(2)
    void testGetStudentByIdSuccess() throws Exception {
        when(studentService.getStudentById("S001")).thenReturn(student);
        mockMvc.perform(get("/api/students/S001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("S001"));
    }

    // GET /api/students/{id} — should return 404 for unknown student
    @Test
    @Order(3)
    void testGetStudentByIdNotFound() throws Exception {
        when(studentService.getStudentById("UNKNOWN"))
                .thenThrow(new StudentNotFoundException("UNKNOWN"));
        mockMvc.perform(get("/api/students/UNKNOWN"))
                .andExpect(status().isNotFound());
    }

    // POST /api/students — should return 201 on successful registration
    @Test
    @Order(4)
    void testRegisterStudentSuccess() throws Exception {
        when(studentService.registerStudent(any())).thenReturn(student);
        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"S001\",\"name\":\"Aaliyah Adams\",\"email\":\"aaliyah@email.com\",\"passwordHash\":\"hashed123\",\"phoneNumber\":\"0821234567\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value("S001"));
    }

    // POST /api/students — should return 400 for duplicate email
    @Test
    @Order(5)
    void testRegisterStudentDuplicateEmail() throws Exception {
        when(studentService.registerStudent(any()))
                .thenThrow(new IllegalArgumentException("Email already exists"));
        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"S002\",\"name\":\"Test\",\"email\":\"aaliyah@email.com\",\"passwordHash\":\"hash\",\"phoneNumber\":\"0821234567\"}"))
                .andExpect(status().isBadRequest());
    }

    // DELETE /api/students/{id} — should return 204 on success
    @Test
    @Order(6)
    void testDeleteStudentSuccess() throws Exception {
        doNothing().when(studentService).deleteStudent("S001");
        mockMvc.perform(delete("/api/students/S001"))
                .andExpect(status().isNoContent());
    }

    // DELETE /api/students/{id} — should return 404 for unknown student
    @Test
    @Order(7)
    void testDeleteStudentNotFound() throws Exception {
        doThrow(new StudentNotFoundException("UNKNOWN")).when(studentService).deleteStudent("UNKNOWN");
        mockMvc.perform(delete("/api/students/UNKNOWN"))
                .andExpect(status().isNotFound());
    }

    // GET /api/students/active — should return 200 with students who have an active enrollment
    @Test
    @Order(23)
    void testGetActiveStudents() throws Exception {
        when(studentService.getActiveStudents()).thenReturn(List.of(student));
        mockMvc.perform(get("/api/students/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value("S001"));
    }

    // ─── Course endpoints ────────────────────────────────────────────────────

    // GET /api/courses — should return 200 with a list of courses
    @Test
    @Order(8)
    void testGetAllCourses() throws Exception {
        when(courseService.getAllCourses()).thenReturn(List.of(course));
        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseId").value("C001"));
    }

    // GET /api/courses/{id} — should return 200 for a known course
    @Test
    @Order(9)
    void testGetCourseByIdSuccess() throws Exception {
        when(courseService.getCourseById("C001")).thenReturn(course);
        mockMvc.perform(get("/api/courses/C001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId").value("C001"));
    }

    // GET /api/courses/{id} — should return 404 for unknown course
    @Test
    @Order(10)
    void testGetCourseByIdNotFound() throws Exception {
        when(courseService.getCourseById("UNKNOWN"))
                .thenThrow(new CourseNotFoundException("UNKNOWN"));
        mockMvc.perform(get("/api/courses/UNKNOWN"))
                .andExpect(status().isNotFound());
    }

    // GET /api/courses/active — should return 200 with active courses
    @Test
    @Order(11)
    void testGetActiveCourses() throws Exception {
        when(courseService.getActiveCourses()).thenReturn(List.of(course));
        mockMvc.perform(get("/api/courses/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseId").value("C001"));
    }

    // POST /api/courses — should return 201 on successful creation
    @Test
    @Order(12)
    void testCreateCourseSuccess() throws Exception {
        when(courseService.createCourse(any())).thenReturn(course);
        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"courseId\":\"C001\",\"title\":\"Classic Lash Extensions\",\"description\":\"desc\",\"category\":\"LASH\",\"durationDays\":3,\"price\":1500.00}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.courseId").value("C001"));
    }

    // DELETE /api/courses/{id} — should return 204 on deactivation
    @Test
    @Order(13)
    void testDeactivateCourseSuccess() throws Exception {
        doNothing().when(courseService).deactivateCourse("C001");
        mockMvc.perform(delete("/api/courses/C001"))
                .andExpect(status().isNoContent());
    }

    // DELETE /api/courses/{id} — should return 409 if already inactive
    @Test
    @Order(14)
    void testDeactivateAlreadyInactiveCourse() throws Exception {
        doThrow(new IllegalStateException("Course already inactive"))
                .when(courseService).deactivateCourse("C001");
        mockMvc.perform(delete("/api/courses/C001"))
                .andExpect(status().isConflict());
    }

    // ─── Enrollment endpoints ─────────────────────────────────────────────────

    // GET /api/enrollments — should return 200 with a list of enrollments
    @Test
    @Order(15)
    void testGetAllEnrollments() throws Exception {
        when(enrollmentService.getAllEnrollments()).thenReturn(List.of(enrollment));
        mockMvc.perform(get("/api/enrollments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].enrollmentId").value("E001"));
    }

    // GET /api/enrollments/{id} — should return 200 for a known enrollment
    @Test
    @Order(16)
    void testGetEnrollmentByIdSuccess() throws Exception {
        when(enrollmentService.getEnrollmentById("E001")).thenReturn(enrollment);
        mockMvc.perform(get("/api/enrollments/E001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.enrollmentId").value("E001"));
    }

    // GET /api/enrollments/{id} — should return 404 for unknown enrollment
    @Test
    @Order(17)
    void testGetEnrollmentByIdNotFound() throws Exception {
        when(enrollmentService.getEnrollmentById("UNKNOWN"))
                .thenThrow(new EnrollmentNotFoundException("UNKNOWN"));
        mockMvc.perform(get("/api/enrollments/UNKNOWN"))
                .andExpect(status().isNotFound());
    }

    // POST /api/enrollments — should return 201 on successful enrollment
    @Test
    @Order(18)
    void testEnrollStudentSuccess() throws Exception {
        when(enrollmentService.enrollStudent(any())).thenReturn(enrollment);
        mockMvc.perform(post("/api/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"enrollmentId\":\"E001\",\"student\":{\"userId\":\"S001\",\"name\":\"Aaliyah Adams\",\"email\":\"aaliyah@email.com\",\"passwordHash\":\"hash\",\"phoneNumber\":\"082\"},\"course\":{\"courseId\":\"C001\",\"title\":\"Classic Lash\",\"description\":\"desc\",\"category\":\"LASH\",\"durationDays\":3,\"price\":1500.00}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.enrollmentId").value("E001"));
    }

    // POST /api/enrollments — should return 409 for duplicate enrollment
    @Test
    @Order(19)
    void testEnrollStudentDuplicate() throws Exception {
        when(enrollmentService.enrollStudent(any()))
                .thenThrow(new DuplicateEnrollmentException("S001", "C001"));
        mockMvc.perform(post("/api/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"enrollmentId\":\"E002\",\"student\":{\"userId\":\"S001\",\"name\":\"Aaliyah Adams\",\"email\":\"aaliyah@email.com\",\"passwordHash\":\"hash\",\"phoneNumber\":\"082\"},\"course\":{\"courseId\":\"C001\",\"title\":\"Classic Lash\",\"description\":\"desc\",\"category\":\"LASH\",\"durationDays\":3,\"price\":1500.00}}"))
                .andExpect(status().isConflict());
    }

    // POST /api/enrollments — should return 400 for inactive course
    @Test
    @Order(20)
    void testEnrollStudentInactiveCourse() throws Exception {
        when(enrollmentService.enrollStudent(any()))
                .thenThrow(new CourseNotActiveException("C001"));
        mockMvc.perform(post("/api/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"enrollmentId\":\"E003\",\"student\":{\"userId\":\"S001\",\"name\":\"Aaliyah Adams\",\"email\":\"aaliyah@email.com\",\"passwordHash\":\"hash\",\"phoneNumber\":\"082\"},\"course\":{\"courseId\":\"C001\",\"title\":\"Classic Lash\",\"description\":\"desc\",\"category\":\"LASH\",\"durationDays\":3,\"price\":1500.00}}"))
                .andExpect(status().isBadRequest());
    }

    // PUT /api/enrollments/{id}/activate — should return 200 on activation
    @Test
    @Order(21)
    void testActivateEnrollmentSuccess() throws Exception {
        enrollment.activate();
        when(enrollmentService.activateEnrollment("E001")).thenReturn(enrollment);
        mockMvc.perform(put("/api/enrollments/E001/activate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    // DELETE /api/enrollments/{id} — should return 204 on cancellation
    @Test
    @Order(22)
    void testCancelEnrollmentSuccess() throws Exception {
        doNothing().when(enrollmentService).cancelEnrollment("E001");
        mockMvc.perform(delete("/api/enrollments/E001"))
                .andExpect(status().isNoContent());
    }
}