package com.studentms.studentms.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nama;
    @Column
    private int nim;
    @Column
    private String alamat;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date tanggallahir;

    public StudentModel(String nama, int nim, String alamat, Date tanggallahir) {
        this.nama = nama;
        this.nim = nim;
        this.alamat = alamat;
        this.tanggallahir = tanggallahir;
    }
}
