package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("savechat")
    public @ResponseBody ResponseEntity<?> savemesage(@RequestParam("name") String name,
                                                   @RequestParam("email") String email,
                                                   @RequestParam("subject") String subject,
                                                   @RequestParam("message") String message) throws IOException {
        ApiResponse apiResponse = contactService.savemesage(name,email,subject,message);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

}
