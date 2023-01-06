package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.Tadbirlar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TadbirlarRepository extends JpaRepository<Tadbirlar,Integer> {
    boolean existsBySarlavha(String sarlavha);
}
