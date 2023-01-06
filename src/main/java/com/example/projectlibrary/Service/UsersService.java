package com.example.projectlibrary.Service;

import com.example.projectlibrary.Payload.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {
    ApiResponse saveUser(String ismandfamilya,String yosh, String seriya, String email, String number, String parol);

    ApiResponse deleteUser(Integer id);

    ApiResponse selectUser(Integer id);

    ApiResponse tekshirUser(String username, String password);
}
