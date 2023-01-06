package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.XizmatKorsatish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XizmatKorsatishRepository extends JpaRepository<XizmatKorsatish,Integer> {
    boolean existsByManzil(String manzil);
}
