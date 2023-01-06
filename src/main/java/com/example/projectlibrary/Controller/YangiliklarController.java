package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Entayti.Yangilik;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.YangilikRepazitory;
import com.example.projectlibrary.Service.YangilikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class
YangiliklarController {
    @Autowired
    YangilikService yangilikService;

    @Autowired
    YangilikRepazitory yangilikRepazitory;

    @PostMapping("/savanew")
    public @ResponseBody ResponseEntity<?> savenew(@RequestParam("yangilikNomi") String nomi,
                                                   @RequestParam("yangilikDateTime") String date,
                                                   @RequestParam("kitobMuqova") MultipartFile file,
                                                   @RequestParam("izoh") String izoh) throws IOException {

        ApiResponse apiResponse = yangilikService.saveNew(nomi,date,file,izoh);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @DeleteMapping("/newDelete/{id}")
    public HttpEntity<?> newDelete(@PathVariable Integer id){
        ApiResponse apiResponse = yangilikService.newDeleteId(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @GetMapping("/fullNews/{id}")
    public String fullNews(@PathVariable Integer id , Model map){

        List<Yangilik> list = yangilikRepazitory.findAll();
        for(Yangilik y : list){
            if (y.getId().equals(id)) map.addAttribute("Fullnews1",y);
        }
        return "News/fullNews";
    }

    @GetMapping("/selectNews/{id}")
    public HttpEntity<?> selectNews(@PathVariable Integer id){
        ApiResponse apiResponse = yangilikService.selectNewsFull(id);
        return ResponseEntity.status(apiResponse.isSuccess()?2000:208).body(apiResponse.getObject());
    }

    @PutMapping("/editnews")
    public @ResponseBody ResponseEntity<?> editnews11(@RequestParam("editId") Integer editId,
                                                   @RequestParam("editName") String editName,
                                                   @RequestParam("editIzoh") String editIzoh,
                                                   @RequestParam("editMuqova") MultipartFile editMuqova) throws IOException {
        ApiResponse apiResponse = yangilikService.editNews(editId,editName,editIzoh,editMuqova);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }
}
