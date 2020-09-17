package ru.itis.dtos;

import lombok.Data;

@Data
public class UpdatedManualDto {
    private Long id;
    private String title;
    private String htmlText;
}
