package com.training.petfood.test;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> userList = userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> getById(@PathVariable String id) {
        int userId = Integer.parseInt(id);
        Optional<UserDto> foundUsers = userRepository.findById(userId);
        return new ResponseEntity<>(foundUsers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> getByName(@RequestParam(name = "name") String name) {
        List<UserDto> foundUsers = userRepository.findByName(name);
        return new ResponseEntity<>(foundUsers, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDto> create(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        Integer salary = Integer.parseInt(body.get("salary"));
        UserDto createdUser = userRepository.save(new UserDto(name, salary));
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable String id) {
        int userId = Integer.parseInt(id);
        UserDto userToDelete = userRepository.findById(userId).orElse(null);
        userRepository.delete(userToDelete);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody Map<String, String> body) {
        int userId = Integer.parseInt(id);
        try {
            UserDto userToUpdate = userRepository.findById(userId).orElse(null);
            try {
                userToUpdate.setName(body.get("name"));
                userToUpdate.setSalary(Integer.parseInt(body.get("salary")));
                UserDto updatedUser = userRepository.save(userToUpdate);
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } catch (Error error) {
                return new ResponseEntity<UserDto>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Error error) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity createUsersFromFile(
            @NotNull @RequestParam("file") MultipartFile multipartFile
    ) throws IOException, InvalidFormatException {
        InputStream excelFile = multipartFile.getInputStream();
        Workbook workbook = WorkbookFactory.create(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        sheet.forEach(row -> {
            if (row.getCell(0).getCellTypeEnum().compareTo(CellType.STRING) == 0 &&
                    row.getCell(1).getCellTypeEnum().compareTo(CellType.NUMERIC) == 0) {
                userRepository.save(
                        new UserDto(
                                row.getCell(0).getStringCellValue(),
                                (int) row.getCell(1).getNumericCellValue()
                        )
                );
            }
        });
        workbook.close();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
