package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.Rents;
import com.example.projectlibrary.Entayti.Userlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentsRepository extends JpaRepository<Rents,Integer> {

}
