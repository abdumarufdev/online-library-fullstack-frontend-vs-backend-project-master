package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.AboutUs;
import com.example.projectlibrary.Entayti.AboutUsSourse;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.AboutUsRepasitoey;
import com.example.projectlibrary.Repozitory.AboutUsSourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AboutUsServicelmpl implements AboutUsService{

    @Autowired
    AboutUsRepasitoey aboutUsRepasitoey;

    @Autowired
    AboutUsSourseRepository sourseRepository;
    @Override
    public ApiResponse saveAboutUs1(String mavzu, MultipartFile muqova, String izoh) throws IOException {
        boolean b = aboutUsRepasitoey.existsByMavzu(mavzu);
        if (b) return new ApiResponse("warning",false);
        AboutUs aboutUs = new AboutUs();
        aboutUs.setMavzu(mavzu);
        aboutUs.setMalumot(izoh);

        AboutUsSourse sourse = new AboutUsSourse();
        sourse.setContentType(muqova.getContentType());
        sourse.setRasm(muqova.getBytes());
        sourseRepository.save(sourse);
        aboutUs.setAboutUsSourse(sourse);
        aboutUsRepasitoey.save(aboutUs);
        return new ApiResponse("succesfull",true);
    }

    @Override
    public ApiResponse deleteAboutus(Integer id) {
        Optional<AboutUs> optionalAboutUs = aboutUsRepasitoey.findById(id);
        if (!optionalAboutUs.isPresent()) return new ApiResponse("error",false);
        aboutUsRepasitoey.deleteById(id);
        sourseRepository.deleteById(id);
        return new ApiResponse("successfull",true);
    }

    @Override
    public ApiResponse editAboutUs(String mavzu, MultipartFile muqova, String izoh, Integer id) throws IOException {
        Optional<AboutUs> optionalAboutUs = aboutUsRepasitoey.findById(id);

        List<AboutUs> list = aboutUsRepasitoey.findAll();
        for (AboutUs a : list){
            if (!a.getId().equals(id)){
                if (a.getMavzu().equals(mavzu)) return new ApiResponse("warning",false);
            }
        }

        if (!optionalAboutUs.isPresent()) return new ApiResponse("null",false);

        AboutUs aboutUs = optionalAboutUs.get();
        aboutUs.setMavzu(mavzu);
        aboutUs.setMalumot(izoh);

        if (!muqova.isEmpty()){
            AboutUsSourse sourse = new AboutUsSourse();
            sourse.setContentType(muqova.getContentType());
            sourse.setRasm(muqova.getBytes());
            sourseRepository.save(sourse);
            aboutUs.setAboutUsSourse(sourse);
        }
        aboutUsRepasitoey.save(aboutUs);
        return new ApiResponse("success",true);
    }

    @Override
    public ApiResponse selectXizmat() {
            List<AboutUs> list = aboutUsRepasitoey.findAll();
            int count=0;
            for (AboutUs x: list){
                count++;
            }
            return new ApiResponse("succesfull",count);
        }
    }

