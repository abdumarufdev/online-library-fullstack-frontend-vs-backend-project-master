package com.example.projectlibrary.Entayti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TadbirlarSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ContentType;
    private byte[] muqova;

    private String muqovaContentType1;
    private byte[] rasm1;

    private String muqovaContentType2;
    private byte[] rasm2;

    private String muqovaContentType3;
    private byte[] rasm3;
}
