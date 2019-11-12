package com.training.petfood.services;

import com.training.petfood.models.Product;
import java.util.List;

public interface IProductService {

    public List<Product> getAllProducts();

    public Product getProductById(Integer id);

    public List<Product> getProductByName(String name);

    public Product createProduct(Product product);

    public Product updateProduct(Product product, Product productToUpdate);

    public void deleteProduct(Product productToDelete);
}
