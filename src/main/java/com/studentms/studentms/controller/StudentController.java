package com.studentms.studentms.controller;

import com.studentms.studentms.model.StudentModel;
import com.studentms.studentms.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    public static final String USER_LOGIN = "userLogin";
    private final StudentService studentService;

    @GetMapping
    public String getStudentPage(@RequestParam(required = false, name = "login") String login,
                                 @RequestParam(required = false) String email,
                                 Model model,
                                 HttpServletRequest request){

        HttpSession session = request.getSession();
        if(login != null && !login.isEmpty()){
            session.setAttribute(USER_LOGIN, login);
        }

        String userLogin = (String) session.getAttribute(USER_LOGIN);

        model.addAttribute(USER_LOGIN, login);

        List<StudentModel> students = studentService.getAllStudentsByLogin(userLogin);

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

    @GetMapping("/edit/{nama}")
    public String getEditStudentPage(Model model, @PathVariable String nama){
        studentService.findByNama(nama);
        StudentModel byNama = studentService.findByNama(nama);
        model.addAttribute("studentToEdit", byNama);
        return "edit_student_page";
    }

    @PostMapping("/editStudent")
    public String editStudent(@ModelAttribute("studentToEdit") StudentModel student){
        studentService.edit(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{nama}")
    public String delete(@PathVariable String nama){
        studentService.delete(nama);
        return "redirect:/students";
    }
}
