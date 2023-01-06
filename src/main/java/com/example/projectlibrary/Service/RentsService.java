package com.example.projectlibrary.Service;

import com.example.projectlibrary.Payload.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface RentsService {
    ApiResponse saveIjara(Integer kitobId, Integer userId, String mudat, String kitobNomi, String kitobMualifi, String ismvsfam, String seriya, String telefon);

    ApiResponse editMudats(String mudatEdit, Integer idEdit);

    ApiResponse saveIjaraFull(String berilganSana, String qaytarilganSana, Integer editId);

    ApiResponse berIjaraga(Integer id, String qaytarilganSana, String berilganSana);

    ApiResponse qaytiIjara(Integer id);

    ApiResponse trueAndfalse(Integer id);

    ApiResponse Select(Integer id);

    ApiResponse berOl(Integer id);
}
