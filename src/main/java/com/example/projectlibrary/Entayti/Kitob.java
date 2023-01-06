package com.example.projectlibrary.Entayti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Kitob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nomi;

    @Column(nullable = false)
    private String muallif;

    @Column(nullable = false)
    private String janri;

    @Column(nullable = false)
    private String yil;

    @Column(nullable = false)
    private String katagoriya;

    @Column(nullable = false)
    private int hajmi;

    @OneToOne(fetch = FetchType.LAZY)
    KitobSours kitobSours;

    public Kitob(String nomi, String muallif, String janri, String yil, String katagoriya, int hajmi, KitobSours kitobSours) {
        this.nomi = nomi;
        this.muallif = muallif;
        this.janri = janri;
        this.yil = yil;
        this.katagoriya = katagoriya;
        this.hajmi = hajmi;
        this.kitobSours = kitobSours;
    }
}
