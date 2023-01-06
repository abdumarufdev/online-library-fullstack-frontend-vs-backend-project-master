package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.IjaraBookRepazitory;
import com.example.projectlibrary.Repozitory.IjaraBookSourseRepazitory;
import com.example.projectlibrary.Service.IjaraBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class IjaraBookController {
    @Autowired
    IjaraBookRepazitory bookRepazitory;

    @Autowired
    IjaraBookSourseRepazitory sourseRepazitory;

    @Autowired
    IjaraBookService ijaraBookService;

    @PostMapping("/ijarabook")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("kitobNomi") String kitobNomi,
                                                         @RequestParam("kitobMuallifi") String kitobMuallifi,
                                                         @RequestParam("janr") String janr,
                                                         @RequestParam("kitobDate") String kitobDate,
                                                         @RequestParam("mudat") String mudat,
                                                         @RequestParam("kitobMuqova") MultipartFile image,
                                                         @RequestParam("katagoriya") String katagoriya) throws IOException {
        ApiResponse apiResponse = ijaraBookService.saveBook(kitobNomi,kitobMuallifi,janr,kitobDate,image,katagoriya,mudat);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }


    @DeleteMapping("/ijara/delete/{id}")
    public HttpEntity<?> deleteBooks(@PathVariable Integer id){
        ApiResponse apiResponse = ijaraBookService.deleteBooks(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }


    @PutMapping("/ijara/book/edit")
    public @ResponseBody ResponseEntity<?> editnews11(@RequestParam("editId12") Integer editId,
                                                      @RequestParam("nomiEdit") String nomiEdit,
                                                      @RequestParam("muallifEdit") String muallifEdit,
                                                      @RequestParam("janrEdit") String janrEdit,
                                                      @RequestParam("muqovaEdit") MultipartFile muqovaEdit,
                                                      @RequestParam("katagoriyaEdit") String katagoriyaEdit,
                                                      @RequestParam("editMudat") String editMudat
    ) throws IOException {
        ApiResponse apiResponse = ijaraBookService.editBook(editId, nomiEdit, muallifEdit, janrEdit, muqovaEdit, katagoriyaEdit,editMudat);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 208).body(apiResponse.getMessage());
    }
}
