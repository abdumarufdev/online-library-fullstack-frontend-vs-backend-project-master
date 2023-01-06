package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.AboutUs;
import com.example.projectlibrary.Entayti.AboutUsSourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutUsSourseRepository extends JpaRepository<AboutUsSourse,Integer> {
}
