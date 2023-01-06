package com.example.projectlibrary.Entayti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class XizmatKorsatish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String manzil;

    @Column(nullable = false)
    private String telefon;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String kuni;

    @Column(nullable = false)
    private String vaqi;
}
