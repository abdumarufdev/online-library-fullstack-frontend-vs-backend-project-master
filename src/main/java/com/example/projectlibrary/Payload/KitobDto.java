package com.example.projectlibrary.Payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class KitobDto {

    private String nomi;

    private String muallif;

    private String katagoriya;

    private String yil;

    private MultipartFile imageByte;

    private MultipartFile fileByte;

}
