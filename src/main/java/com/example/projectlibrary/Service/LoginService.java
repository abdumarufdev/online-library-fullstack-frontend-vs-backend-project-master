package com.example.projectlibrary.Service;

import com.example.projectlibrary.Payload.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    ApiResponse insertLogin(String username, String password, String takPassword);

    ApiResponse editLogin(Integer idEdit12, String editUser12, String editPassword, String editTasPassword);

    ApiResponse deleteLogin(Integer id);

    ApiResponse tekshir(String username, String password);
}
