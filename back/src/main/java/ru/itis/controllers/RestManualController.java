package ru.itis.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dtos.NewManualDto;
import ru.itis.services.ManualService;

@RestController
@Slf4j
public class RestManualController {

    @Autowired
    private ManualService manualService;

    @PostMapping("/api/manual/create")
    public ResponseEntity<?> createManual(@RequestBody NewManualDto newManualDto) {
        try {
            manualService.create(newManualDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
