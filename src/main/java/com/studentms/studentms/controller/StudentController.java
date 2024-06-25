package com.studentms.studentms.controller;

import com.studentms.studentms.model.StudentModel;
import com.studentms.studentms.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    public static final String USER_LOGIN = "userLogin";

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String getStudentPage(@RequestParam(required = false, name = "login") String login,
                                 @RequestParam(required = false) String email,
                                 Model model,
                                 HttpServletRequest request){

//        HttpSession session = request.getSession();
//        if(login != null && !login.isEmpty()){
//            session.setAttribute(USER_LOGIN, login);
//        }
//
//        String userLogin = (String) session.getAttribute(USER_LOGIN);
//
//        model.addAttribute(USER_LOGIN, login);

//        List<StudentModel> students = studentService.getAllStudentsByLogin(userLogin);
        List<StudentModel> students = studentService.getAllStudents();
        model.addAttribute("userStudents", students);
        return "student_page";
    }
    @GetMapping("/create")
    public String createStudentPage(Model model){
        model.addAttribute("newStudent", new StudentModel());
        return "create_student_page";
    }

    @PostMapping("/createStudent")
    public String createStudent(@ModelAttribute("newStudent") StudentModel student){
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String getEditStudentPage(Model model, @PathVariable Long id){
        StudentModel byNama = studentService.findById(id);
        model.addAttribute("studentToEdit", byNama);
        return "edit_student_page";
    }

    @PostMapping("/editStudent")
    public String editStudent(@ModelAttribute("studentToEdit") StudentModel student){
        studentService.edit(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        studentService.delete(id);
        return "redirect:/students";
    }
}
