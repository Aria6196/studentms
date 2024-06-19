package com.studentms.studentms.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {

    private String nama;
    private int nim;
    private String alamat;
    private String tanggallahir;
}
