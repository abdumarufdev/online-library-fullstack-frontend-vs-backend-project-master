package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.Userlar;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServicelmpl implements UsersService{
    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse saveUser(String ismandfamilya,String yosh, String seriya, String email, String number, String parol) {
        boolean b = userRepository.existsByEmail(email);
        if (b) return new ApiResponse("warning",false);
        Userlar users = new Userlar();
        users.setIsmvsfamilya(ismandfamilya);
        users.setYosh(yosh);
        users.setSeriya(seriya);
        users.setEmail(email);
        users.setTelefon(number);
        users.setParol(parol);
        userRepository.save(users);
        return new ApiResponse("success full",true);
    }

    @Override
    public ApiResponse deleteUser(Integer id) {
        Optional<Userlar> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent()) return new ApiResponse("topilmadi",false);
        userRepository.deleteById(id);
        return new ApiResponse("success full",true);
    }

    @Override
    public ApiResponse selectUser(Integer id) {
        Optional<Userlar> optionalUserlar = userRepository.findById(id);
        if (!optionalUserlar.isPresent()) return new ApiResponse("error" ,false);
        return new ApiResponse("success",true);
    }

    @Override
    public ApiResponse tekshirUser(String username, String password) {
        int id = 0;
        List<Userlar> list = userRepository.findAll();
        for (Userlar u : list){
            if (u.getEmail().equals(username)){
                id = u.getId();
            }
        }
        boolean b = userRepository.existsByEmailAndParol(username, password);
        if (!b) return new ApiResponse("error",false);
        return new ApiResponse("success",true,id);
    }

}
