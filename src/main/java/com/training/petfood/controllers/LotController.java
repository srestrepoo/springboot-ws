package com.training.petfood.controllers;

import com.training.petfood.models.Lot;
import com.training.petfood.models.Product;
import com.training.petfood.repositories.LotRepository;
import com.training.petfood.repositories.ProductRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lot")
public class LotController {

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/upload")
    public ResponseEntity createLotsFromFile(
            @NotNull @RequestParam("file") MultipartFile multipartFile
    ) throws IOException, InvalidFormatException {

        InputStream excelFile = multipartFile.getInputStream();
        Workbook workbook = WorkbookFactory.create(excelFile);
        try {
            Sheet sheet = workbook.getSheetAt(0);
            ArrayList<Lot> lotList = new ArrayList<Lot>();
            sheet.forEach(row -> {
                Product product = productRepository.findById((int) row.getCell(1).getNumericCellValue()).orElse(null);
                lotList.add(
                        Lot.builder()
                                .id((int) row.getCell(0).getNumericCellValue())
                                .product(product)
                                .totalAmount((int) row.getCell(2).getNumericCellValue())
                                .creationDate(row.getCell(3).getDateCellValue())
                                .expirationDate(row.getCell(4).getDateCellValue())
                                .campusId((int) row.getCell(5).getNumericCellValue())
                                .build());
            });
            lotRepository.saveAll(lotList);
        } catch(Error error) {
            throw error;
        } finally {
            workbook.close();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Lot>> getAll() {
        List<Lot> lotList = lotRepository.findAll();
        return new ResponseEntity<>(lotList, HttpStatus.OK);
    }
}
