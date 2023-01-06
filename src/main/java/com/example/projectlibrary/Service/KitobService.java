package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.Kitob;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Payload.KitobDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface KitobService {
    ApiResponse saveBooks(KitobDto kitobDto) throws IOException;

    ApiResponse saveBooks(String kitobNomi, String kitobMuallifi, String janr, String kitobDate, MultipartFile image, MultipartFile file, String katagoriya) throws IOException;

    List<Kitob> getAllKitob();

    ApiResponse selectBooks(Integer id);

    ApiResponse deleteBooks(Integer id);

    ApiResponse editNews(Integer editId, String nomiEdit, String muallifEdit, String janrEdit, MultipartFile muqovaEdit, MultipartFile fileEdit, String katagoriyaEdit) throws IOException;
}
