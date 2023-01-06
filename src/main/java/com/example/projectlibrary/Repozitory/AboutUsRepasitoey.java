package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.AboutUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface AboutUsRepasitoey extends JpaRepository<AboutUs,Integer> {
    boolean existsByMavzu(String mavzu);
}
