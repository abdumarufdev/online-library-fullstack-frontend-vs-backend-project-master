package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.IjaraBook;
import com.example.projectlibrary.Entayti.Kitob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IjaraBookRepazitory extends JpaRepository<IjaraBook,Integer> {
    boolean existsByNomiAndMuallif(String nomi, String muallif);
    List<IjaraBook> findByKatagoriya(String katagoriya);
}
