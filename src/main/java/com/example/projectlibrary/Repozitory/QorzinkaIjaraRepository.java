package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.QorzinkaIjara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QorzinkaIjaraRepository extends JpaRepository<QorzinkaIjara,Integer> {
}
