package com.example.projectlibrary.Entayti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Bolim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String sarlavha;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String text;

    @OneToOne
    BolimSource bolimSource;
}
