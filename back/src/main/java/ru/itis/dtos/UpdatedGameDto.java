package ru.itis.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdatedGameDto {
    private Long id;
    private String name;
    private String description;
    private MultipartFile img;
}
