package com.example.projectlibrary.Service;

import com.example.projectlibrary.Payload.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface ContactService {
    ApiResponse savemesage(String name, String email, String subject, String message);
}
