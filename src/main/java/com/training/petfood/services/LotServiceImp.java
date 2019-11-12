package com.training.petfood.services;

import com.training.petfood.models.Lot;
import com.training.petfood.models.Product;
import com.training.petfood.repositories.LotRepository;
import com.training.petfood.repositories.ProductRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class LotServiceImp implements ILotService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    LotRepository lotRepository;

    @Override
    public List<Lot> createLotsFromFile(MultipartFile xlsxStream) throws IOException, InvalidFormatException {
        InputStream excelFile = xlsxStream.getInputStream();
        Workbook workbook = WorkbookFactory.create(excelFile);
        ArrayList<Lot> lotList = new ArrayList<Lot>();
        try {
            Sheet sheet = workbook.getSheetAt(0);
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
            return lotList;
        }
    }

    @Override
    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }
}
