package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Service.QrcodebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class QrcodebookController {
    @Autowired
    QrcodebookService qrcodebookService;

    @PostMapping("/qrcode/book")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("kitobNomi") String kitobNomi,
                                                         @RequestParam("kitobMuallifi") String kitobMuallifi,
                                                         @RequestParam("janr") String janr,
                                                         @RequestParam("kitobDate") String kitobDate,
                                                         @RequestParam("kitobMuqova") MultipartFile image,
                                                         @RequestParam("katagoriya") String katagoriya) throws IOException {
        ApiResponse apiResponse = qrcodebookService.saveBook(kitobNomi,kitobMuallifi,janr,kitobDate,image,katagoriya);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @DeleteMapping("/qr/delete/{id}")
    public HttpEntity<?> deleteBooks(@PathVariable Integer id){
        ApiResponse apiResponse = qrcodebookService.deleteBooks(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @PutMapping("/qr/books")
    public @ResponseBody ResponseEntity<?> editnews11(@RequestParam("editId12") Integer editId,
                                                      @RequestParam("nomiEdit") String nomiEdit,
                                                      @RequestParam("muallifEdit") String muallifEdit,
                                                      @RequestParam("janrEdit") String janrEdit,
                                                      @RequestParam("muqovaEdit") MultipartFile muqovaEdit,
                                                      @RequestParam("katagoriyaEdit") String katagoriyaEdit) throws IOException {
        ApiResponse apiResponse = qrcodebookService.editBookQr(editId, nomiEdit, muallifEdit, janrEdit, muqovaEdit, katagoriyaEdit);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 208).body(apiResponse.getMessage());
    }
}
