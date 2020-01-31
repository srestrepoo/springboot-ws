package com.training.petfood.controllers;

import com.training.petfood.models.Product;
import com.training.petfood.repositories.ProductRepository;
import com.training.petfood.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        List<Product> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id) {
        Product foundProduct = productService.getProductById(id);
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> getByName(@RequestParam(name = "name") String name) {
        List<Product> foundProducts = productService.getProductByName(name);
        return new ResponseEntity<>(foundProducts, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            Product productToDelete = productService.getProductById(id);
            if(productToDelete != null) {
                productService.deleteProduct(productToDelete);
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch(Error error) {
            throw error;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product product) {
        try {
            Product productToUpdate = productService.getProductById(id);
            if ( productToUpdate != null) {
                Product updatedProduct = productService.updateProduct(product, productToUpdate);
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Error error) {
            throw error;
        }
    }
}
