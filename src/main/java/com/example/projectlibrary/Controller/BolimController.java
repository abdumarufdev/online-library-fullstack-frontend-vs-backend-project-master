package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Service.BolimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class BolimController {
    @Autowired
    BolimService bolimService;

    @PostMapping("/bolim")
    public @ResponseBody ResponseEntity<?> savemesage(@RequestParam("bolimSarlavha") String bolimSarlavha,
                                                      @RequestParam("bolimMuqova") MultipartFile bolimMuqova,
                                                      @RequestParam("insertText") String insertText) throws IOException {
        ApiResponse apiResponse = bolimService.saveBolim(bolimSarlavha,bolimMuqova,insertText);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @GetMapping("/select/bolim")
    public HttpEntity<?> Select(){
        ApiResponse apiResponse = bolimService.selectXizmat();
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getObject());
    }

    @PutMapping("/edit/bolim")
    public @ResponseBody ResponseEntity<?> editbolim(@RequestParam("editSarlavha") String editSarlavha,
                                                      @RequestParam("editMuqova") MultipartFile editMuqova,
                                                      @RequestParam("idEdit") Integer idEdit,
                                                      @RequestParam("editText") String editText) throws IOException {
        ApiResponse apiResponse = bolimService.editBolim(editSarlavha,editMuqova,idEdit,editText);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

}
