package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.Kitob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KitobRepazitory extends JpaRepository<Kitob,Integer> {
    boolean existsByNomiAndMuallif(String nomi, String muallif);

    List<Kitob> findByKatagoriya(String katagoriya);
    Optional<Kitob> findById(Integer id);
}
