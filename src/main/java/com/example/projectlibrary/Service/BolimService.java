package com.example.projectlibrary.Service;

import com.example.projectlibrary.Payload.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface BolimService {
    ApiResponse saveBolim(String bolimSarlavha, MultipartFile bolimMuqova, String insertText) throws IOException;

    ApiResponse selectXizmat();

    ApiResponse editBolim(String editSarlavha, MultipartFile editMuqova, Integer idEdit, String editText) throws IOException;
}
