package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.IjaraBook;
import com.example.projectlibrary.Entayti.Qrcodebook;
import com.example.projectlibrary.Entayti.QrcodebookSourse;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.QrcodebookRepazitory;
import com.example.projectlibrary.Repozitory.QrcodebookSourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class QrcodebookServicelmpl implements QrcodebookService{

    @Autowired
    QrcodebookRepazitory qrcodebookRepazitory;

    @Autowired
    QrcodebookSourseRepository sourseRepository;


    @Override
    public ApiResponse saveBook(String kitobNomi, String kitobMuallifi, String janr, String kitobDate, MultipartFile image, String katagoriya) throws IOException {
        boolean b = qrcodebookRepazitory.existsByNomiAndMuallif(kitobNomi, kitobMuallifi);
        if (b) return new ApiResponse("error",false);
        Qrcodebook qrcodebook = new Qrcodebook();
        qrcodebook.setNomi(kitobNomi);
        qrcodebook.setMuallif(kitobMuallifi);
        qrcodebook.setJanri(janr);
        qrcodebook.setYil(kitobDate);
        qrcodebook.setKatagoriya(katagoriya);

        QrcodebookSourse sourse = new QrcodebookSourse();

        sourse.setContentType(image.getContentType());
        sourse.setImageByte(image.getBytes());

        sourseRepository.save(sourse);
        qrcodebook.setQrcodebookSourse(sourse);

        qrcodebookRepazitory.save(qrcodebook);
        return new ApiResponse("success",true);
    }

    @Override
    public ApiResponse deleteBooks(Integer id) {
        Optional<Qrcodebook> optionalQrcodebook = qrcodebookRepazitory.findById(id);
        if (!optionalQrcodebook.isPresent()) return new ApiResponse("Topilmadi",false);
        qrcodebookRepazitory.deleteById(id);
        sourseRepository.deleteById(id);
        return new ApiResponse("succesfull",true);
    }

    @Override
    public ApiResponse editBookQr(Integer editId12, String nomiEdit, String muallifEdit, String janrEdit, MultipartFile muqovaEdit, String katagoriyaEdit) throws IOException {
        Optional<Qrcodebook> optionalQrcodebook = qrcodebookRepazitory.findById(editId12);

        List<Qrcodebook> list = qrcodebookRepazitory.findAll();
        for (Qrcodebook q: list){
            if (!q.getId().equals(editId12)){
                if (q.getNomi().equals(nomiEdit) && q.getMuallif().equals(muallifEdit)){
                    return new ApiResponse("Bunday QR kitob mavjud",false);
                }
            }
        }

        if (!optionalQrcodebook.isPresent()) return new ApiResponse("Malumot topilmadi!",false);

        Qrcodebook qrcodebook = optionalQrcodebook.get();
        qrcodebook.setNomi(nomiEdit);
        qrcodebook.setMuallif(muallifEdit);
        qrcodebook.setJanri(janrEdit);
        qrcodebook.setKatagoriya(katagoriyaEdit);

        if (!muqovaEdit.isEmpty()){
            QrcodebookSourse sourse = new QrcodebookSourse();
            sourse.setContentType(muqovaEdit.getContentType());
            sourse.setImageByte(muqovaEdit.getBytes());
            sourseRepository.save(sourse);
            qrcodebook.setQrcodebookSourse(sourse);
        }

        qrcodebookRepazitory.save(qrcodebook);
        return new ApiResponse("success",true);
    }
}
