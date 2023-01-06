package com.example.projectlibrary.Service;

import com.example.projectlibrary.Payload.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface AboutUsService {
    ApiResponse saveAboutUs1(String mavzu, MultipartFile muqova, String izoh) throws IOException;

    ApiResponse deleteAboutus(Integer id);

    ApiResponse editAboutUs(String mavzu, MultipartFile muqova, String izoh, Integer id) throws IOException;

    ApiResponse selectXizmat();
}
