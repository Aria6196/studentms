package com.studentms.studentms.service;

import com.studentms.studentms.model.StudentModel;
import com.studentms.studentms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    private static List<StudentModel> students;

    static {
        students = new ArrayList<>();
        students.add(new StudentModel("Aria Octavian Hamza", 25, "NH", "29 Oktober 2004"));
        students.add(new StudentModel("Aria Hamza", 26, "H", "29 Oktober 2004"));
        students.add(new StudentModel("Aria Hamza", 27, "N", "29 Oktober 2004"));
    }

    public List<StudentModel> getAllStudentsByLogin(String login) {
        if (login != null) {
            // Logika berdasarkan login bisa ditambahkan di sini
            return students;
        }

        return students.stream()
                .filter(student -> student.getNim() > 25)
                .toList();
    }

    public List<StudentModel> getAllStudents() {
        return studentRepository.findAll();
    }

    public void save(StudentModel student) {
        studentRepository.save(student);
    }

    public StudentModel findByNama(String nama) {
        return students.stream()
                .filter(it -> it.getNama().equals(nama))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    public void edit(StudentModel student) {
        StudentModel existingStudent = findByNama(student.getNama());
        existingStudent.setNim(student.getNim());
        existingStudent.setAlamat(student.getAlamat());
        existingStudent.setTanggallahir(student.getTanggallahir());
    }

    public void delete(String nama) {
        StudentModel student = findByNama(nama);
        students.remove(student);
    }
}
