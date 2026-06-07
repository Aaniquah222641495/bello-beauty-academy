package api;

import exceptions.StudentNotFoundException;
import models.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.StudentService;

import java.util.List;

// thin controller — all business logic stays in StudentService
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    // constructor injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /api/students — return all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // GET /api/students/active — return only students with an active enrollment
    @GetMapping("/active")
    public ResponseEntity<List<Student>> getActiveStudents() {
        return ResponseEntity.ok(studentService.getActiveStudents());
    }

    // GET /api/students/{id} — return a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // POST /api/students — register a new student
    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student created = studentService.registerStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/students/{id} — update an existing student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id,
                                                 @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    // DELETE /api/students/{id} — delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // handle StudentNotFoundException — returns 404
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleNotFound(StudentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // handle IllegalArgumentException — returns 400
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}