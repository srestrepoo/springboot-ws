package com.training.petfood.services;

import com.training.petfood.models.Lot;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ILotService {

    public List<Lot> createLotsFromFile(MultipartFile xlsxStream) throws IOException, InvalidFormatException;

    public List<Lot> getAllLots();
}
