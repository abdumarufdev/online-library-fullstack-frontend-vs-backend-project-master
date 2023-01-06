package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.IjaraBook;
import com.example.projectlibrary.Entayti.IjaraBookSourse;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.IjaraBookRepazitory;
import com.example.projectlibrary.Repozitory.IjaraBookSourseRepazitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class IjaraBookServicelmpl implements IjaraBookService{
    @Autowired
    IjaraBookRepazitory bookRepazitory;

    @Autowired
    IjaraBookSourseRepazitory sourseRepazitory;
    @Override
    public ApiResponse saveBook(String kitobNomi, String kitobMuallifi, String janr, String kitobDate, MultipartFile image, String katagoriya,String mudat) throws IOException {
        boolean b = bookRepazitory.existsByNomiAndMuallif(kitobNomi, kitobMuallifi);
        if (b) return new ApiResponse("Bunday kitob mavjud",false);
        IjaraBook ijaraBook = new IjaraBook();
        ijaraBook.setNomi(kitobNomi);
        ijaraBook.setMuallif(kitobMuallifi);
        ijaraBook.setJanri(janr);
        ijaraBook.setYil(kitobDate);
        ijaraBook.setKatagoriya(katagoriya);
        ijaraBook.setIjaramudati(mudat);

        IjaraBookSourse sourse = new IjaraBookSourse();
        sourse.setContentType(image.getContentType());
        sourse.setImageByte(image.getBytes());
        sourseRepazitory.save(sourse);

        ijaraBook.setIjaraBookSourse(sourse);
        bookRepazitory.save(ijaraBook);

        return new ApiResponse("SuccessFull",true);
    }

    @Override
    public ApiResponse deleteBooks(Integer id) {
        Optional<IjaraBook> optionalIjaraBook = bookRepazitory.findById(id);
        if (!optionalIjaraBook.isPresent()) return new ApiResponse("Topilmadi",false);
        bookRepazitory.deleteById(id);
        sourseRepazitory.deleteById(id);
        return new ApiResponse("succesfull",true);
    }

    @Override
    public ApiResponse editBook(Integer editId, String nomiEdit, String muallifEdit, String janrEdit, MultipartFile muqovaEdit, String katagoriyaEdit, String editMudat) throws IOException {

        Optional<IjaraBook> optionalIjaraBook = bookRepazitory.findById(editId);
        List<IjaraBook> list = bookRepazitory.findAll();

        for (IjaraBook i: list){
            if (!i.getId().equals(editId)){
                if (i.getNomi().equals(nomiEdit) && i.getMuallif().equals(muallifEdit)){
                    return new ApiResponse("Bunday kitob mavjud",false);
                }
            }
        }

        if (!optionalIjaraBook.isPresent()) return new ApiResponse("Malumot topilmadi!",false);

        IjaraBook ijaraBook = optionalIjaraBook.get();
        ijaraBook.setNomi(nomiEdit);
        ijaraBook.setMuallif(muallifEdit);
        ijaraBook.setJanri(janrEdit);
        ijaraBook.setKatagoriya(katagoriyaEdit);
        ijaraBook.setIjaramudati(editMudat);

        if (!muqovaEdit.isEmpty()){
            IjaraBookSourse sourse = new IjaraBookSourse();
            sourse.setContentType(muqovaEdit.getContentType());
            sourse.setImageByte(muqovaEdit.getBytes());
            sourseRepazitory.save(sourse);
            ijaraBook.setIjaraBookSourse(sourse);
        }

        bookRepazitory.save(ijaraBook);
        return new ApiResponse("Success Full",true);
    }
}
