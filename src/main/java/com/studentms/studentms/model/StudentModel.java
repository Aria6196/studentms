package com.studentms.studentms.model;


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
    @Column
    private String tanggallahir;

    public StudentModel(String nama, int nim, String alamat, String tanggallahir) {
        this.nama = nama;
        this.nim = nim;
        this.alamat = alamat;
        this.tanggallahir = tanggallahir;
    }
}
