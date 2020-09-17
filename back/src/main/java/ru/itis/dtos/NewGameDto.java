package ru.itis.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewGameDto {
    private String name;
    private String description;
    private MultipartFile img;
}
