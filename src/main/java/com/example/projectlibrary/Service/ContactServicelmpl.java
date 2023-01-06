package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.Contact;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.ContactRepazitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServicelmpl implements ContactService{
    @Autowired
    ContactRepazitory contactRepazitory;
    @Override
    public ApiResponse savemesage(String name, String email, String subject, String message) {
        boolean b = contactRepazitory.existsByNameAndSubject(name, subject);
        if (b) return new ApiResponse("Bunday taklif mavjud!",false);

        Contact contact = new Contact();

        contact.setName(name);
        contact.setEmail(email);
        contact.setSubject(subject);
        contact.setMessage(message);

        contactRepazitory.save(contact);
        return new ApiResponse("SuccessFull",true);
    }
}
