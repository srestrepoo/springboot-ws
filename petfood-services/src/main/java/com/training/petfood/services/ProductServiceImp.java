package com.training.petfood.services;

import com.training.petfood.models.Product;
import com.training.petfood.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product getProductById(Integer id) {
        Product foundProduct = productRepository.findById(id).orElse(null);
        return foundProduct;
    }

    @Override
    public List<Product> getProductByName(String name) {
        List<Product> foundProducts = productRepository.findByName(name);
        return foundProducts;
    }

    @Override
    public Product createProduct(Product product) {
        Product createdProduct = productRepository.save(product);
        return createdProduct;
    }

    @Override
    public Product updateProduct(Product product, Product productToUpdate) {
        productToUpdate.setName(product.getName());
        productToUpdate.setBrand(product.getBrand());
        Product updatedProduct = productRepository.save(productToUpdate);
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Product productToDelete) {
        productRepository.delete(productToDelete);
    }
}
