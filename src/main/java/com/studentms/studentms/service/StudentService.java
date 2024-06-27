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

    public StudentModel findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Data tidak ditemujan"));
    }

    public void edit(StudentModel student) {
        studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
