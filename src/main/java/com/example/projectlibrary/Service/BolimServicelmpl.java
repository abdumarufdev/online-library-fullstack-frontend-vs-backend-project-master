package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.AboutUs;
import com.example.projectlibrary.Entayti.Bolim;
import com.example.projectlibrary.Entayti.BolimSource;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.BolimRepository;
import com.example.projectlibrary.Repozitory.BolimSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BolimServicelmpl implements BolimService{

    @Autowired
    BolimRepository bolimRepository;

    @Autowired
    BolimSourceRepository sourceRepository;

    @Override
    public ApiResponse saveBolim(String bolimSarlavha, MultipartFile bolimMuqova, String insertText) throws IOException {
        boolean b = bolimRepository.existsBySarlavha(bolimSarlavha);
        if (b) return new ApiResponse("error",false);
        Bolim bolim = new Bolim();
        bolim.setSarlavha(bolimSarlavha);
        bolim.setText(insertText);

        BolimSource source = new BolimSource();
        source.setContentType(bolimMuqova.getContentType());
        source.setImageByte(bolimMuqova.getBytes());
        sourceRepository.save(source);

        bolim.setBolimSource(source);
        bolimRepository.save(bolim);
        return new ApiResponse("true",true);
    }

    @Override
    public ApiResponse selectXizmat() {
        List<Bolim> list = bolimRepository.findAll();
        int count=0;
        for (Bolim x: list){
            count++;
        }
        return new ApiResponse("succesfull",count);
    }

    @Override
    public ApiResponse editBolim(String editSarlavha, MultipartFile editMuqova, Integer idEdit, String editText) throws IOException {
        Optional<Bolim> optionalBolim = bolimRepository.findById(idEdit);

        List<Bolim> list = bolimRepository.findAll();
        for (Bolim b : list){
            if (!b.getId().equals(idEdit)){
                if (b.getSarlavha().equals(editSarlavha)){
                    return new ApiResponse("false",false);
                }
            }
        }

        if (!optionalBolim.isPresent()) return new ApiResponse("Error" , false);

        Bolim bolim = optionalBolim.get();
        bolim.setSarlavha(editSarlavha);
        bolim.setText(editText);

        if (!editMuqova.isEmpty()){
            BolimSource source = new BolimSource();
            source.setContentType(editMuqova.getContentType());
            source.setImageByte(editMuqova.getBytes());
            sourceRepository.save(source);
            bolim.setBolimSource(source);
        }

        bolimRepository.save(bolim);
        return new ApiResponse("success full",true);
    }
}
