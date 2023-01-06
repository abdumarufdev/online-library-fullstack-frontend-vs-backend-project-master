package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Service.XizmatKorsatishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class XizmatKorsatishController {

    @Autowired
    XizmatKorsatishService xizmatKorsatishService;

    @PostMapping("/xizmat/service")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("manzil") String manzil,
                                                         @RequestParam("telefon") String telefon,
                                                         @RequestParam("email") String email,
                                                         @RequestParam("kun") String kun,
                                                         @RequestParam("vaqt") String vaqt
                                                        ) throws IOException {
        ApiResponse apiResponse = xizmatKorsatishService.saveManzil(manzil,telefon,email,kun,vaqt);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @DeleteMapping("/xizmat/delete/{id}")
    public HttpEntity<?> deleteBooks(@PathVariable Integer id){
        ApiResponse apiResponse = xizmatKorsatishService.deleteXizmat(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @PutMapping("/xizmat/edit")
    public @ResponseBody ResponseEntity<?> editnews11(@RequestParam("idEdit12") Integer idEdit12,
                                                      @RequestParam("editManzil") String editManzil,
                                                      @RequestParam("editTelefon") String editTelefon,
                                                      @RequestParam("editEmail") String editEmail,
                                                      @RequestParam("editKun") String editKun,
                                                      @RequestParam("editVaqt") String editVaqt
                                                      ) throws IOException {
        ApiResponse apiResponse = xizmatKorsatishService.editXizmat(idEdit12,editManzil,editTelefon,editEmail,editKun,editVaqt);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 208).body(apiResponse.getMessage());
    }

    @GetMapping("/select")
    public HttpEntity<?> Select(){
        ApiResponse apiResponse = xizmatKorsatishService.selectXizmat();
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getObject());
    }

}
