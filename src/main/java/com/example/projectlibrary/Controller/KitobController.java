package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Entayti.Kitob;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.KitobRepazitory;
import com.example.projectlibrary.Repozitory.KitobSoursRepazitory;
import com.example.projectlibrary.Service.KitobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class KitobController {

    @Autowired
    KitobService kitobService;

    @Autowired
    KitobRepazitory kitobRepazitory;

    @PostMapping("/savebook")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("kitobNomi") String kitobNomi, @RequestParam("kitobMuallifi") String kitobMuallifi,
                                                         @RequestParam("janr") String janr, @RequestParam("kitobDate") String kitobDate
            , @RequestParam("kitobMuqova") MultipartFile image, @RequestParam("kitobFile") MultipartFile file, @RequestParam("katagoriya") String katagoriya) throws IOException {
        ApiResponse apiResponse = kitobService.saveBooks(kitobNomi, kitobMuallifi, janr, kitobDate, image, file, katagoriya);
        if (!apiResponse.isSuccess()) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(apiResponse.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse.getMessage());
    }


    @GetMapping("/select/{id}")
    public HttpEntity<?> selectBook(@PathVariable Integer id) {
        ApiResponse apiResponse = kitobService.selectBooks(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 208).body(apiResponse.getObject());
    }


    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteBook(@PathVariable Integer id) {
        ApiResponse apiResponse = kitobService.deleteBooks(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 208).body(apiResponse.getMessage());
    }


    @Autowired
    KitobSoursRepazitory soursRepazitory;

//    @GetMapping("/image/display/{id}")
//    @ResponseBody
//    void showImage(@PathVariable("id") Integer id, HttpServletResponse response)
//            throws ServletException, IOException {
//        Optional<Kitob> byId = kitobRepazitory.findById(id);
//        if (byId.isPresent()){
//            Kitob kitob = byId.get();
//            response.setContentType(kitob.getKitobSours().getContentType());
//            response.getOutputStream().write(kitob.getKitobSours().getImageByte());
//            response.getOutputStream().close();
//        }
//    }

    @GetMapping("/image/show")
    String show(Model map) {
        List<Kitob> kitob = kitobService.getAllKitob();
        map.addAttribute("FullBook", kitob);
        return "Admin/kitoblar";
    }

    @PutMapping("/editbooks")
    public @ResponseBody ResponseEntity<?> editnews11(@RequestParam("editId") Integer editId,
                                                      @RequestParam("nomiEdit") String nomiEdit,
                                                      @RequestParam("muallifEdit") String muallifEdit,
                                                      @RequestParam("janrEdit") String janrEdit,
                                                      @RequestParam("muqovaEdit") MultipartFile muqovaEdit,
                                                      @RequestParam("fileEdit") MultipartFile fileEdit,
                                                      @RequestParam("katagoriyaEdit") String katagoriyaEdit) throws IOException {
        ApiResponse apiResponse = kitobService.editNews(editId, nomiEdit, muallifEdit, janrEdit, muqovaEdit, fileEdit, katagoriyaEdit);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 208).body(apiResponse.getMessage());
    }
}