package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Service.RentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class RentsController {

    @Autowired
    RentsService rentsService;

    @PostMapping("/ijara/olish")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("kitobId") Integer kitobId,
                                                         @RequestParam("userId") Integer userId,
                                                         @RequestParam("kitobNomi") String kitobNomi,
                                                         @RequestParam("kitobMualifi") String kitobMualifi,
                                                         @RequestParam("ismvsfam") String ismvsfam,
                                                         @RequestParam("seriya") String seriya,
                                                         @RequestParam("telefon") String telefon,
                                                         @RequestParam("mudat") String mudat
    ) throws IOException {
        ApiResponse apiResponse = rentsService.saveIjara(kitobId,userId,mudat,kitobNomi,kitobMualifi,ismvsfam,seriya,telefon);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @PostMapping("/ijara/book")
    public @ResponseBody ResponseEntity<?> save(@RequestParam("berilganSana") String berilganSana,
                                                         @RequestParam("qaytarilganSana") String qaytarilganSana,
                                                         @RequestParam("editId") Integer editId
    ) throws IOException {
        ApiResponse apiResponse = rentsService.saveIjaraFull(berilganSana,qaytarilganSana,editId);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

//    @PutMapping("/delete/user/{id}")
//    public HttpEntity<?> deleteBooks(@PathVariable Integer id){
//        ApiResponse apiResponse = rentsService.deleteUser(id);
//        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
//    }
//
//    @GetMapping("/user/select/{id}")
//    public HttpEntity<?> sl(@PathVariable Integer id){
//        ApiResponse apiResponse = usersService.selectUser(id);
//        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getObject());
//    }


    @PutMapping("/ii/oo")
    public @ResponseBody ResponseEntity<?> createProduct123(@RequestParam("editId10") Integer id,
                                                            @RequestParam("qaytarish") String qaytarilganSana,
                                                            @RequestParam("berish") String berilganSana
    ) throws IOException {
        ApiResponse apiResponse = rentsService.berIjaraga(id,qaytarilganSana,berilganSana);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getObject());
    }

    @GetMapping("/qaytarildi/{id}")
    public HttpEntity<?> savek(@PathVariable Integer id) throws IOException {
        ApiResponse apiResponse = rentsService.qaytiIjara(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getObject());
    }

    @PutMapping("/tah")
    public @ResponseBody ResponseEntity<?> editnews11(@RequestParam("mudatEdit") String mudatEdit,
                                                      @RequestParam("idEdit") Integer idEdit
                                                     ) throws IOException {
        ApiResponse apiResponse = rentsService.editMudats(mudatEdit,idEdit);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 208).body(apiResponse.getObject());
    }

    @GetMapping("/true1/{id}")
    public HttpEntity<?> trueAndfalse(@PathVariable Integer id) throws IOException {
        ApiResponse apiResponse = rentsService.trueAndfalse(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getObject());
    }

    @PutMapping("/true2/{id}")
    public HttpEntity<?> Select(@PathVariable Integer id) throws IOException {
        ApiResponse apiResponse = rentsService.Select(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getObject());
    }


    @PutMapping("/full/true1")
    public @ResponseBody ResponseEntity<?> create123(@RequestParam("inputaid") Integer id
    ) throws IOException {
        ApiResponse apiResponse = rentsService.berOl(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getObject());
    }

}
