package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.Yangilik;
import com.example.projectlibrary.Entayti.YangilikSourse;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.YangilikRepazitory;
import com.example.projectlibrary.Repozitory.YangilikSourseRepazitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class YangilikServicelmpl implements YangilikService{

    @Autowired
    YangilikRepazitory yangilikRepazitory;

    @Autowired
    YangilikSourseRepazitory yangilikSourseRepazitory;
    @Override
    public ApiResponse saveNew(String nomi, String date, MultipartFile image, String izoh) throws IOException {
        boolean b = yangilikRepazitory.existsByNomi(nomi);
        if (b) return new ApiResponse("Bunday malumot mavjud!",false);
        Yangilik yangilik = new Yangilik();
        yangilik.setNomi(nomi);
        yangilik.setDate(date);
        yangilik.setIzoh(izoh);

        YangilikSourse sourse = new YangilikSourse();
        sourse.setContentType(image.getContentType());
        sourse.setImageByte(image.getBytes());

        YangilikSourse yangilikSourse = yangilikSourseRepazitory.save(sourse);

        yangilik.setYangilikSourse(yangilikSourse);
        yangilikRepazitory.save(yangilik);

        return new ApiResponse("SuccessFull",true);
    }

    @Override
    public ApiResponse newDeleteId(Integer id) {
        boolean b = yangilikRepazitory.existsById(id);
        if (!b) return new ApiResponse("error",false);
        yangilikRepazitory.deleteById(id);
        yangilikSourseRepazitory.deleteById(id);
        return new ApiResponse("SuccessFull",true);
    }

    @Override
    public ApiResponse selectNewsFull(Integer id) {
        Optional<Yangilik> all = yangilikRepazitory.findById(id);
        if (all.isEmpty()) return new ApiResponse("Malumot mavjud emas!",false);
        return new ApiResponse("Successfuly",true,all);
    }

    @Override
    public ApiResponse editNews(Integer id,String editName, String editIzoh, MultipartFile editMuqova) throws IOException {

        Optional<Yangilik> optionalYangilik = yangilikRepazitory.findById(id);
        List<Yangilik> yangiliks = yangilikRepazitory.findAll();
        for (Yangilik y : yangiliks){
            if (!y.getId().equals(id)){
                if (y.getNomi().equals(editName)) return new ApiResponse("Bunday malumot mavjud",false);
            }
        }

        if (!optionalYangilik.isPresent()) return new ApiResponse("Malumot topilmadi!",false);


        Yangilik yangilik = optionalYangilik.get();
        yangilik.setNomi(editName);
        yangilik.setIzoh(editIzoh);


        if (!editMuqova.isEmpty()){
            YangilikSourse sourse = new YangilikSourse();
            sourse.setContentType(editMuqova.getContentType());
            sourse.setImageByte(editMuqova.getBytes());
            YangilikSourse sourse1 = yangilikSourseRepazitory.save(sourse);
            yangilik.setYangilikSourse(sourse1);
        }

        yangilikRepazitory.save(yangilik);
        return new ApiResponse("SuccessFull",true);

    }


}
