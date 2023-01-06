package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Service.AboutUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class AboutUsController {
    @Autowired
    AboutUsService aboutUsService;

    @PostMapping("/aboutus")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("mavzu") String mavzu,
                                                         @RequestParam("muqova") MultipartFile muqova,
                                                         @RequestParam("izoh") String izoh
                                                        ) throws IOException {
        ApiResponse apiResponse = aboutUsService.saveAboutUs1(mavzu,muqova,izoh);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @DeleteMapping("/aboutus/{id}")
    public HttpEntity<?> deleteBooks(@PathVariable Integer id){
        ApiResponse apiResponse = aboutUsService.deleteAboutus(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }


    @PutMapping("/aboutus/edit")
    public @ResponseBody ResponseEntity<?> editnews11(@RequestParam("editMavzu") String mavzu,
                                                      @RequestParam("editMuqova") MultipartFile muqova,
                                                      @RequestParam("editIzoh") String izoh,
                                                      @RequestParam("editId") Integer id
    ) throws IOException {
        ApiResponse apiResponse = aboutUsService.editAboutUs(mavzu,muqova,izoh,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 208).body(apiResponse.getMessage());
    }


    @GetMapping("/select/tadbir")
    public HttpEntity<?> Select(){
        ApiResponse apiResponse = aboutUsService.selectXizmat();
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getObject());
    }
}
