package com.training.petfood.controllers;

import com.training.petfood.models.Lot;
import com.training.petfood.services.ILotService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lot")
public class LotController {

    @Autowired
    private ILotService lotService;

    @PostMapping("/upload")
    public ResponseEntity createLotsFromFile(@NotNull @RequestParam("file") MultipartFile multipartFile) throws IOException, InvalidFormatException {
        List<Lot> lotList = lotService.createLotsFromFile(multipartFile);
        return new ResponseEntity<>(lotList, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Lot>> getAll() {
        List<Lot> lotList = lotService.getAllLots();
        return new ResponseEntity<>(lotList, HttpStatus.OK);
    }
}
