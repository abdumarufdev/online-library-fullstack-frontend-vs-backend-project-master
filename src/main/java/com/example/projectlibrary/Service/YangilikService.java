package com.example.projectlibrary.Service;

import com.example.projectlibrary.Payload.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface YangilikService {
    ApiResponse saveNew(String nomi, String date, MultipartFile image, String izoh) throws IOException;

    ApiResponse newDeleteId(Integer id);

    ApiResponse selectNewsFull(Integer id);

    ApiResponse editNews(Integer id,String editName, String editIzoh, MultipartFile editMuqova) throws IOException;
}
