package com.example.mvcdemo.controller;

import com.example.mvcdemo.model.Student;
import com.example.mvcdemo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * CONTROLLER - Student Controller
 * Handles HTTP requests related to students
 */
@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    // Constructor Injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Display all students
     * URL: GET /students
     */
    @GetMapping
    public String listStudents(Model model) {
        // Get data from service (MODEL)
        List<Student> students = studentService.getAllStudents();
        int studentCount = studentService.getStudentCount();

        // Add data to model for VIEW
        model.addAttribute("students", students);
        model.addAttribute("studentCount", studentCount);
        model.addAttribute("pageTitle", "Student List");

        // Return view name (VIEW)
        return "students";  // Resolves to /WEB-INF/views/students.jsp
    }

    /**
     * Show add student form
     * URL: GET /students/add
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // Create empty student object for form binding
        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Add Student");

        return "add-student";  // Resolves to /WEB-INF/views/add-student.jsp
    }

    /**
     * Process add student form
     * URL: POST /students/add
     */
    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") Student student,
                             Model model) {
        // Save student through service
        studentService.addStudent(student);

        // Add success message
        model.addAttribute("message", "Student added successfully!");

        // Since it is a redirect the model attributes will not be available in the redirected page. 

        // Redirect to student list
        return "redirect:/students";
    }

    /**
     * Show edit student form
     * URL: GET /students/edit/{id}
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        // Get student by ID from service
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("pageTitle", "Edit Student");
 
        return "edit-student";
    }

    /**
     * Process edit student form
     * URL: POST /students/update
     */
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") Student student,
                                Model model) {
        // Update student through service
        studentService.updateStudent(student);  
        model.addAttribute("message", "Student updated successfully!");

        // return "redirect:/students"; // uses the http redirect instead of forward.
        return listStudents(model);
    }

    /**
     * Delete student
     * URL: GET /students/delete/{id}
     */    
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
        // Delete student through service
        studentService.deleteStudent(id);
        model.addAttribute("message", "Student deleted successfully!");
 
        // return "redirect:/students"; // uses the http redirect instead of forward.
        return listStudents(model);
    }
}