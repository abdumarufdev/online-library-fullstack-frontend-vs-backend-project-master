package com.example.projectlibrary.Entayti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AboutUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String mavzu;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String malumot;

    @OneToOne
    AboutUsSourse aboutUsSourse;
}
