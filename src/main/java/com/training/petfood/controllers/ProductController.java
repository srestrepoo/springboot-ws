package com.training.petfood.controllers;

import com.training.petfood.models.Product;
import com.training.petfood.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        List<Product> productList = productRepository.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable String id) {
        int userId = Integer.parseInt(id);
        Product foundProduct = productRepository.findById(userId).orElse(null);
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> getByName(@RequestParam(name = "name") String name) {
        List<Product> foundProducts = productRepository.findByName(name);
        return new ResponseEntity<>(foundProducts, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> create(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String brand = body.get("brand");
        Product createdProduct = productRepository.save(new Product(name, brand));
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable String id) {
        int productId = Integer.parseInt(id);
        Product productToDelete = productRepository.findById(productId).orElse(null);
        productRepository.delete(productToDelete);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody Map<String, String> body) {
        int productId = Integer.parseInt(id);
        try {
            Product productToUpdate = productRepository.findById(productId).orElse(null);
            try {
                productToUpdate.setName(body.get("name"));
                productToUpdate.setBrand(body.get("brand"));
                Product updatedProduct = productRepository.save(productToUpdate);
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } catch (Error error) {
                return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Error error) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }
}
