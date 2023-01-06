package com.example.projectlibrary.Entayti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Yangilik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomi;
    private String date;

    @Column(columnDefinition = "TEXT" , nullable = false)
    private String izoh;

    @OneToOne
    YangilikSourse yangilikSourse;

}
