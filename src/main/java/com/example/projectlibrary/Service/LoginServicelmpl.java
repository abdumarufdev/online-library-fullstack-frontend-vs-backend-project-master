package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.Login;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.LoginRepository;
import lombok.extern.java.Log;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginServicelmpl implements LoginService{
    @Autowired
    LoginRepository loginRepository;
    @Override
    public ApiResponse insertLogin(String username, String password, String takPassword) {
        if (!password.equals(takPassword)) return new ApiResponse("Parolar bir hil emas!",false);
        boolean b = loginRepository.existsByUsername(username);
        if (b) return new ApiResponse("username mavjud",false);
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);
        loginRepository.save(login);
        return new ApiResponse("success full",true);
    }

    @Override
    public ApiResponse editLogin(Integer idEdit12, String editUser12, String editPassword, String editTasPassword) {

        if (!editPassword.equals(editTasPassword)) return new ApiResponse("Parollar mos emas",false);
        Optional<Login> optionalLogin = loginRepository.findById(idEdit12);
        Login login = optionalLogin.get();
        if (!login.getUsername().equals(editUser12)) return new ApiResponse("username bir xil emas",false);
        login.setPassword(editPassword);
        loginRepository.save(login);
        return new ApiResponse("success full",true);
    }

    @Override
    public ApiResponse deleteLogin(Integer id) {
        Optional<Login> optionalLogin = loginRepository.findById(id);
        if (!optionalLogin.isPresent()) return new ApiResponse("Malumot topilmadi",false);
        loginRepository.deleteById(id);
        return new ApiResponse("Success full",true);
    }

    @Override
    public ApiResponse tekshir(String username, String password) {
        boolean b = loginRepository.existsByUsernameAndPassword(username, password);
        if (!b) return new ApiResponse("login yoki parol xato!",false);
        return new ApiResponse("Success full",true);
    }
}
