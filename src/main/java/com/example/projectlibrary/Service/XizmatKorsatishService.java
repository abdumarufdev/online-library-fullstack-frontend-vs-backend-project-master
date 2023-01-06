package com.example.projectlibrary.Service;

import com.example.projectlibrary.Payload.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface XizmatKorsatishService {
    ApiResponse saveManzil(String manzil, String telefon, String email, String kun, String vaqt);

    ApiResponse deleteXizmat(Integer id);

    ApiResponse editXizmat(Integer idEdit12, String editManzil, String editTelefon, String editEmail, String editKun, String editVaqt);

    ApiResponse selectXizmat();
}
