package com.studentms.studentms.service;

import com.studentms.studentms.model.StudentModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private static List<StudentModel> students;

    static{
        students = new ArrayList<>();
        students.add(new StudentModel("Aria Octavian Hamza", 25, "NH", "29 Oktober 2004"));
        students.add(new StudentModel("Aria Hamza", 26, "H", "29 Oktober 2004"));
        students.add(new StudentModel("Aria  Hamza", 27, "N", "29 Oktober 2004"));
    }

    public List<StudentModel> getAllStudentsByLogin(String login){
        if(login != null){
            return students;
        }

        return students.stream()
                .filter(student->student.getNim() > 25)
                .toList();
    }

    public void save(StudentModel student) {
        students.add(student);
    }

    public StudentModel findByNama(String nama) {
        StudentModel studentModel = students.stream()
                .filter(it -> it.getNama().equals(nama))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        students.remove(studentModel);
        return studentModel;
    }

    public void edit(StudentModel student) {
        save(student);
    }

    public void delete(String nama) {
        findByNama(nama);
    }
}
