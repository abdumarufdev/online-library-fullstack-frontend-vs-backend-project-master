package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.Kitob;
import com.example.projectlibrary.Entayti.KitobSours;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Payload.KitobDto;
import com.example.projectlibrary.Repozitory.KitobRepazitory;
import com.example.projectlibrary.Repozitory.KitobSoursRepazitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.awt.SystemColor.info;

@Service
public class KitobServicelmpl implements KitobService{
    @Autowired
    KitobRepazitory kitobRepazitory;

    @Autowired
    KitobSoursRepazitory soursRepazitory;

    @Override
    public ApiResponse saveBooks(KitobDto kitobDto) throws IOException {
//        boolean b = kitobRepazitory.existsByNomiAndMuallif(kitobDto.getNomi(), kitobDto.getMuallif());
//        if(b) return new ApiResponse("Bunday kitob mavjud",false);
//
//        Kitob kitob = new Kitob();
//
//        kitob.setMuallif(kitobDto.getMuallif());
//        kitob.setKatagoriya(kitobDto.getKatagoriya());
//        kitob.setNomi(kitobDto.getNomi());
//        kitob.setYil(kitobDto.getYil());
//
//        KitobSours kitobSours = new KitobSours();
//        kitobSours.setFileByte(kitobDto.getFileByte().getBytes());
//        kitobSours.setImageByte(kitobDto.getImageByte().getBytes());
//        kitobSours.setContentType(kitobDto.getImageByte().getContentType());
//
//        KitobSours sours = soursRepazitory.save(kitobSours);
//        kitob.setKitobSours(sours);
//
//        kitobRepazitory.save(kitob);
        return new ApiResponse("Successfull",true);
    }

    @Override
    public ApiResponse saveBooks(String kitobNomi, String kitobMuallifi, String janr, String kitobDate, MultipartFile image, MultipartFile file, String katagoriya) throws IOException {
        boolean b = kitobRepazitory.existsByNomiAndMuallif(kitobNomi,kitobMuallifi);
        if(b) return new ApiResponse("Bunday kitob mavjud",false);

        Kitob kitob = new Kitob();
        kitob.setNomi(kitobNomi);
        kitob.setMuallif(kitobMuallifi);
        kitob.setJanri(janr);
        kitob.setKatagoriya(katagoriya);
        kitob.setYil(kitobDate);

        KitobSours sours = new KitobSours();
        sours.setContentType(image.getContentType());
        sours.setImageByte(image.getBytes());
        sours.setFileContentType(file.getContentType());
        sours.setFileByte(file.getBytes());
        sours.setArginalName(file.getOriginalFilename());
//        ImageGalleryByte imageByte=new ImageGalleryByte();

//        imageByte.setFileByte(file.getBytes());
//        imageByte.setFileContentType(file.getContentType());
        String[] content=file.getOriginalFilename().split("\\.");
        sours.setArginalName(kitobNomi+"."+content[1]);
        kitob.setHajmi((int) (file.getSize()/1024));

//        imageByte.setImageByte(image.getBytes());
//        imageByte.setContentType(image.getContentType());

//        ImageGalleryByte galleryByte = imageGalleryRepositoryByte.save(imageByte);

//        kitob.setHajmi((int) file.getSize());

        KitobSours kitobSours = soursRepazitory.save(sours);
        kitob.setKitobSours(kitobSours);

        kitobRepazitory.save(kitob);
        return new ApiResponse("SuccessFull",true);
    }

    @Override
    public List<Kitob> getAllKitob() {
        return kitobRepazitory.findAll();
    }

    @Override
    public ApiResponse selectBooks(Integer id) {
        Optional<Kitob> optionalKitob = kitobRepazitory.findById(id);

        if (!optionalKitob.isPresent()) return new ApiResponse("Bunday kitob topilmadi",false);

        return new ApiResponse("SuccessFull",true,optionalKitob.get());
    }

    @Override
    public ApiResponse deleteBooks(Integer id) {
        Optional<Kitob> optionalKitob = kitobRepazitory.findById(id);
        if (!optionalKitob.isPresent()) return new ApiResponse("Topilmadi",false);
        kitobRepazitory.deleteById(id);
        soursRepazitory.deleteById(id);
        return new ApiResponse("SuccessFull",true);
    }

    @Override
    public ApiResponse editNews(Integer editId, String nomiEdit, String muallifEdit, String janrEdit, MultipartFile muqovaEdit, MultipartFile fileEdit, String katagoriyaEdit) throws IOException {

        Optional<Kitob> optionalKitob = kitobRepazitory.findById(editId);
        List<Kitob> list = kitobRepazitory.findAll();
        for (Kitob k : list){
            if (!k.getId().equals(editId)){
                if (k.getNomi().equals(nomiEdit) && k.getMuallif().equals(muallifEdit)){
                    return new ApiResponse("Bunday malumot mavjud!",false);
                }
            }
        }

        if (!optionalKitob.isPresent()) return new ApiResponse("Malumot topilmadi!",false);


        Kitob kitob = optionalKitob.get();
        KitobSours sours = kitobRepazitory.findById(optionalKitob.get().getKitobSours().getId()).get().getKitobSours();

            if (!muqovaEdit.isEmpty() && !fileEdit.isEmpty()){
                sours.setContentType(muqovaEdit.getContentType());
                sours.setImageByte(muqovaEdit.getBytes());
                sours.setArginalName(fileEdit.getOriginalFilename());
                sours.setFileContentType(fileEdit.getContentType());
                sours.setFileByte(fileEdit.getBytes());
                kitob.setHajmi((int) (fileEdit.getSize()/1024));
                soursRepazitory.save(sours);
                kitob.setKitobSours(sours);
            }
            if (!muqovaEdit.isEmpty()){
                sours.setContentType(muqovaEdit.getContentType());
                sours.setImageByte(muqovaEdit.getBytes());
                sours.setFileContentType(sours.getFileContentType());
                sours.setArginalName(sours.getArginalName());
                sours.setFileByte(sours.getFileByte());
                soursRepazitory.save(sours);
                kitob.setKitobSours(sours);
            }
            if (!fileEdit.isEmpty()){
                sours.setArginalName(fileEdit.getOriginalFilename());
                sours.setFileContentType(fileEdit.getContentType());
                sours.setFileByte(fileEdit.getBytes());
                sours.setContentType(sours.getContentType());
                sours.setImageByte(sours.getImageByte());
                soursRepazitory.save(sours);
                kitob.setKitobSours(sours);
            }

        kitob.setNomi(nomiEdit);
        kitob.setMuallif(muallifEdit);
        kitob.setJanri(janrEdit);
        kitob.setKatagoriya(katagoriyaEdit);

        sours.setContentType(sours.getContentType());
        sours.setImageByte(sours.getImageByte());
        sours.setFileContentType(sours.getFileContentType());
        sours.setArginalName(sours.getArginalName());
        sours.setFileByte(sours.getFileByte());
        soursRepazitory.save(sours);
        kitob.setKitobSours(sours);
        kitobRepazitory.save(kitob);
        return new ApiResponse("Success Full",true);
    }


}
