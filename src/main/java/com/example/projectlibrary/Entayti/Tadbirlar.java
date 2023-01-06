package com.example.projectlibrary.Entayti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tadbirlar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String sarlavha;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String text;

    @Column(nullable = false)
    private String sana;

    @Column(nullable = false)
    private String holat;

    @OneToOne
    TadbirlarSource tadbirlarSource;
}
