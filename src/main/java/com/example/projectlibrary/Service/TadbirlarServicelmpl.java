package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.IjaraBook;
import com.example.projectlibrary.Entayti.Tadbirlar;
import com.example.projectlibrary.Entayti.TadbirlarSource;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.TadbirlarRepository;
import com.example.projectlibrary.Repozitory.TadbirlarSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TadbirlarServicelmpl implements TadbirlarService{

    @Autowired
    TadbirlarRepository tadbirlarRepository;

    @Autowired
    TadbirlarSourceRepository sourceRepository;

    @Override
    public ApiResponse saveTadbir(String sarlavha, String text, String sana, MultipartFile tadbirMuqova, MultipartFile muqova1, MultipartFile muqova2, MultipartFile muqova3) throws IOException {
        boolean b = tadbirlarRepository.existsBySarlavha(sarlavha);
        if (b) return new ApiResponse("false",false);
        Tadbirlar tadbirlar = new Tadbirlar();
        tadbirlar.setSarlavha(sarlavha);
        tadbirlar.setText(text);
        tadbirlar.setSana(sana);
        tadbirlar.setHolat("false");

        TadbirlarSource source = new TadbirlarSource();

        source.setContentType(tadbirMuqova.getContentType());
        source.setMuqova(tadbirMuqova.getBytes());

        source.setMuqovaContentType1(muqova1.getContentType());
        source.setRasm1(muqova1.getBytes());

        source.setMuqovaContentType2(muqova2.getContentType());
        source.setRasm2(muqova2.getBytes());

        source.setMuqovaContentType3(muqova3.getContentType());
        source.setRasm3(muqova3.getBytes());

        sourceRepository.save(source);

        tadbirlar.setTadbirlarSource(source);
        tadbirlarRepository.save(tadbirlar);

        return new ApiResponse("true",true);
    }

    @Override
    public ApiResponse editTadbir(String editSarlavha, MultipartFile muqova, MultipartFile muqova1, MultipartFile muqova2, MultipartFile muqova3, String editText, Integer id) throws IOException {

        Optional<Tadbirlar> optionalTadbirlar = tadbirlarRepository.findById(id);

        List<Tadbirlar> list = tadbirlarRepository.findAll();
        for (Tadbirlar t: list){
            if (!t.getId().equals(id)){
                if (t.getSarlavha().equals(editSarlavha)){
                    return new ApiResponse("Bunday tadbir mavjud",false);
                }
            }
        }

        if (!optionalTadbirlar.isPresent()) return new ApiResponse("false",false);

        Tadbirlar tadbirlar = optionalTadbirlar.get();
        TadbirlarSource source = tadbirlarRepository.findById(optionalTadbirlar.get().getTadbirlarSource().getId()).get().getTadbirlarSource();

        if (!muqova.isEmpty() && !muqova1.isEmpty() && !muqova2.isEmpty() && !muqova3.isEmpty()){
            source.setContentType(muqova.getContentType());
            source.setMuqova(muqova.getBytes());

            source.setMuqovaContentType1(muqova1.getContentType());
            source.setRasm1(muqova1.getBytes());

            source.setMuqovaContentType2(muqova2.getContentType());
            source.setRasm2(muqova2.getBytes());

            source.setMuqovaContentType3(muqova3.getContentType());
            source.setRasm3(muqova3.getBytes());

            sourceRepository.save(source);
            tadbirlar.setTadbirlarSource(source);
        }

        if (!muqova.isEmpty()){
            source.setContentType(muqova.getContentType());
            source.setMuqova(muqova.getBytes());

            sourceRepository.save(source);
            tadbirlar.setTadbirlarSource(source);
        }
        if (!muqova1.isEmpty()){
            source.setMuqovaContentType1(muqova1.getContentType());
            source.setRasm1(muqova1.getBytes());
            sourceRepository.save(source);
            tadbirlar.setTadbirlarSource(source);
        }
        if (!muqova2.isEmpty()){
            source.setMuqovaContentType2(muqova2.getContentType());
            source.setRasm2(muqova2.getBytes());
            sourceRepository.save(source);
            tadbirlar.setTadbirlarSource(source);
        }
        if (!muqova3.isEmpty()){
            source.setMuqovaContentType3(muqova3.getContentType());
            source.setRasm3(muqova3.getBytes());
            sourceRepository.save(source);
            tadbirlar.setTadbirlarSource(source);
        }

        tadbirlar.setSarlavha(editSarlavha);
        tadbirlar.setText(editText);
        tadbirlar.setSana(tadbirlar.getSana());
        tadbirlarRepository.save(tadbirlar);
        return new ApiResponse("true",true);
    }

    @Override
    public ApiResponse deleteTadbir(Integer id) {
        Optional<Tadbirlar> optionalIjaraBook = tadbirlarRepository.findById(id);
        if (!optionalIjaraBook.isPresent()) return new ApiResponse("Topilmadi",false);
        tadbirlarRepository.deleteById(id);
        sourceRepository.deleteById(id);
        return new ApiResponse("succesfull",true);
    }

    @Override
    public ApiResponse select(Integer id) {
        Optional<Tadbirlar> optionalTadbirlar = tadbirlarRepository.findById(id);
        if (optionalTadbirlar.isEmpty()) return new ApiResponse("Malumot topilmadi!",false);
        Tadbirlar tadbirlar = optionalTadbirlar.get();
        if (tadbirlar.getHolat().equals("true")) return new ApiResponse("Bosh oynaga tadbir chiqarilgan!",false);
        List<Tadbirlar> list = tadbirlarRepository.findAll();
        for (Tadbirlar t:list){
            if (!t.getId().equals(id)){
                if (t.getHolat().equals("true")){
                    Optional<Tadbirlar> optionalTadbirlar1 = tadbirlarRepository.findById(t.getId());
                    Tadbirlar m = optionalTadbirlar1.get();
                    m.setHolat("false");
                    tadbirlarRepository.save(m);
                }
            }
        }
        tadbirlar.setHolat("true");
        tadbirlarRepository.save(tadbirlar);
        return new ApiResponse("true",true);
    }
}
