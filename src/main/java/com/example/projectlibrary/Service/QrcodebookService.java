package com.example.projectlibrary.Service;

import com.example.projectlibrary.Payload.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface QrcodebookService {
    ApiResponse saveBook(String kitobNomi, String kitobMuallifi, String janr, String kitobDate, MultipartFile image, String katagoriya) throws IOException;

    ApiResponse deleteBooks(Integer id);

    ApiResponse editBookQr(Integer editId, String nomiEdit, String muallifEdit, String janrEdit, MultipartFile muqovaEdit, String katagoriyaEdit) throws IOException;
}
