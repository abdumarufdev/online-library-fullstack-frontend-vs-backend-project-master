package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.Yangilik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface YangilikRepazitory extends JpaRepository<Yangilik,Integer> {
     boolean existsByNomi(String nomi);
     boolean existsById(Integer id);

     Optional<Yangilik> findByNomi(String nomi);
}
