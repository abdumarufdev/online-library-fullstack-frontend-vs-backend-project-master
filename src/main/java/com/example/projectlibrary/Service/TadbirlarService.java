package com.example.projectlibrary.Service;

import com.example.projectlibrary.Payload.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface TadbirlarService {
    ApiResponse saveTadbir(String sarlavha, String text, String sana, MultipartFile tadbirMuqova, MultipartFile muqova1, MultipartFile muqova2, MultipartFile muqova3) throws IOException;

    ApiResponse editTadbir(String editSarlavha, MultipartFile editMuqova, MultipartFile editMuqova1, MultipartFile editMuqova2, MultipartFile editMuqova3, String editText, Integer id) throws IOException;

    ApiResponse deleteTadbir(Integer id);

    ApiResponse select(Integer id);
}
