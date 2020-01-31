package com.training.petfood.services;

import com.training.petfood.models.Lot;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ILotService {

    List<Lot> createLotsFromFile(MultipartFile xlsxStream) throws IOException, InvalidFormatException;

    List<Lot> getAllLots();
}
