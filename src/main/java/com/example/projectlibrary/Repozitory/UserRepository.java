package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.Userlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Userlar,Integer> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndParol(String email, String parol);
}
