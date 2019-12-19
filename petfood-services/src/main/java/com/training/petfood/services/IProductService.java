package com.training.petfood.services;

import com.training.petfood.models.Product;
import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    Product getProductById(Integer id);

    List<Product> getProductByName(String name);

    Product createProduct(Product product);

    Product updateProduct(Product product, Product productToUpdate);

    void deleteProduct(Product productToDelete);
}
