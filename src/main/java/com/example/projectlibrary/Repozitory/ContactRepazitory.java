package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepazitory extends JpaRepository<Contact,Integer> {
    boolean existsByNameAndSubject(String name, String subject);
}
