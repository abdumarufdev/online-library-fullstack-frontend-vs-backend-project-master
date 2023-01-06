package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.Qrcodebook;
import com.example.projectlibrary.Entayti.XizmatKorsatish;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.XizmatKorsatishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class XizmatKorsatishServicelmpl implements XizmatKorsatishService{
    @Autowired
    XizmatKorsatishRepository xizmatKorsatishRepository;

    @Override
    public ApiResponse saveManzil(String manzil, String telefon, String email, String kun, String vaqt) {
        boolean b = xizmatKorsatishRepository.existsByManzil(manzil);
        if (b) return new ApiResponse("mavjud",false);

        XizmatKorsatish xizmatKorsatish = new XizmatKorsatish();
        xizmatKorsatish.setManzil(manzil);
        xizmatKorsatish.setTelefon(telefon);
        xizmatKorsatish.setEmail(email);
        xizmatKorsatish.setKuni(kun);
        xizmatKorsatish.setVaqi(vaqt);
        xizmatKorsatishRepository.save(xizmatKorsatish);
        return new ApiResponse("successfull",true);
    }

    @Override
    public ApiResponse deleteXizmat(Integer id) {
        Optional<XizmatKorsatish> optionalXizmatKorsatish = xizmatKorsatishRepository.findById(id);
        if (!optionalXizmatKorsatish.isPresent()) return new ApiResponse("Topilmadi",false);
        xizmatKorsatishRepository.deleteById(id);
        return new ApiResponse("succesfull",true);
    }

    @Override
    public ApiResponse editXizmat(Integer idEdit12, String editManzil, String editTelefon, String editEmail, String editKun, String editVaqt) {
        Optional<XizmatKorsatish> optionalXizmatKorsatish = xizmatKorsatishRepository.findById(idEdit12);

        List<XizmatKorsatish> list = xizmatKorsatishRepository.findAll();
        for (XizmatKorsatish x: list){
            if (!x.getId().equals(idEdit12)){
                if (x.getManzil().equals(editManzil)){
                    return new ApiResponse("Bunday manzil mavjud",false);
                }
            }
        }

        if (!optionalXizmatKorsatish.isPresent()) return new ApiResponse("topilmadi",false);
        XizmatKorsatish xizmatKorsatish = optionalXizmatKorsatish.get();
        xizmatKorsatish.setManzil(editManzil);
        xizmatKorsatish.setTelefon(editTelefon);
        xizmatKorsatish.setEmail(editEmail);
        xizmatKorsatish.setKuni(editKun);
        xizmatKorsatish.setVaqi(editVaqt);
        xizmatKorsatishRepository.save(xizmatKorsatish);
        return new ApiResponse("successfull",true);
    }

    @Override
    public ApiResponse selectXizmat() {
        List<XizmatKorsatish> list = xizmatKorsatishRepository.findAll();
        int count=0;
        for (XizmatKorsatish x: list){
            count++;
        }
        return new ApiResponse("succesfull",count);
    }
}