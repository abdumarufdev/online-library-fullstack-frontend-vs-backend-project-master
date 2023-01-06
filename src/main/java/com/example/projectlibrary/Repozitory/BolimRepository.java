package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.Bolim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BolimRepository extends JpaRepository<Bolim,Integer> {
    boolean existsBySarlavha(String sarlavha);
}
