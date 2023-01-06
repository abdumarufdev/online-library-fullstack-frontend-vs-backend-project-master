package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Service.TadbirlarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class TadbirlarController {

    @Autowired
    TadbirlarService tadbirlarService;

    @PostMapping("/tadbir/add")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("sarlavha") String sarlavha,
                                                         @RequestParam("text") String text,
                                                         @RequestParam("sana") String sana,
                                                         @RequestParam("tadbirMuqova") MultipartFile tadbirMuqova,
                                                         @RequestParam("muqova1") MultipartFile muqova1,
                                                         @RequestParam("muqova2") MultipartFile muqova2,
                                                         @RequestParam("muqova3") MultipartFile muqova3
    ) throws IOException {
        ApiResponse apiResponse = tadbirlarService.saveTadbir(sarlavha,text,sana,tadbirMuqova,muqova1,muqova2,muqova3);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @DeleteMapping("/tadbir/delete/{id}")
    public HttpEntity<?> deleteBooks(@PathVariable Integer id){
        ApiResponse apiResponse = tadbirlarService.deleteTadbir(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @PutMapping("/tadbir/edit")
    public @ResponseBody ResponseEntity<?> editnews11(@RequestParam("editSarlavha") String editSarlavha,
                                                      @RequestParam("editMuqova") MultipartFile editMuqova,
                                                      @RequestParam("editMuqova1") MultipartFile editMuqova1,
                                                      @RequestParam("editMuqova2") MultipartFile editMuqova2,
                                                      @RequestParam("editMuqova3") MultipartFile editMuqova3,
                                                      @RequestParam("editText") String editText,
                                                      @RequestParam("idEdit") Integer id
    ) throws IOException {
        ApiResponse apiResponse = tadbirlarService.editTadbir(editSarlavha,editMuqova,editMuqova1,editMuqova2,editMuqova3,editText,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 208).body(apiResponse.getMessage());
    }

    @PutMapping("/select/tadbir/{id}")
    public HttpEntity<?> Select(@PathVariable Integer id){
        ApiResponse apiResponse = tadbirlarService.select(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }
}
