package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/insert")
    public @ResponseBody ResponseEntity<?> insertLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                                                         @RequestParam("takPassword") String takPassword
           ) throws IOException {
        ApiResponse apiResponse = loginService.insertLogin(username,password,takPassword);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @PostMapping("/kirish")
    public @ResponseBody ResponseEntity<?> kirish(@RequestParam("username") String username,
                                                  @RequestParam("password") String password
    ) throws IOException {
        ApiResponse apiResponse = loginService.tekshir(username,password);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @PutMapping("/edit")
    public @ResponseBody ResponseEntity<?> editnews11(@RequestParam("idEdit12") Integer idEdit12,
                                                      @RequestParam("editUser12") String editUser12,
                                                      @RequestParam("editPassword") String editPassword,
                                                      @RequestParam("editTasPassword") String editTasPassword

    ) throws IOException {
        ApiResponse apiResponse = loginService.editLogin(idEdit12,editUser12,editPassword,editTasPassword);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 208).body(apiResponse.getMessage());
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteBook(@PathVariable Integer id) {
        ApiResponse apiResponse = loginService.deleteLogin(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 208).body(apiResponse.getMessage());
    }
}
