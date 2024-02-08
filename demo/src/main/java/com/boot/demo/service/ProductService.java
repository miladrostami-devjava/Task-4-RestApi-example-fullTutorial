package com.boot.demo.service;

import com.boot.demo.entity.Product;
import com.boot.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
//For saving a product in the database
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product newProduct = productRepository.save(product);
        return ResponseEntity.ok(newProduct);
    }
    //For getting all products from the database
    public ResponseEntity<List<Product>> fetchAllProducts(){
        return ResponseEntity.ok(productRepository.findAll());
    }
    //For getting a single product from the database
    public Optional<Product> fetchProductById(Long id){

       Optional<Product> product = productRepository.findById(id);
       return product;

    }

    //For updating a single product from the database
    public ResponseEntity<Product> updateProduct(Long id,Product updatedProduct){
        if (id == null){
            throw new  IllegalArgumentException("Id not found!!");
        }
        Product existingProduct = productRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException(String.valueOf(id)));
existingProduct.setName(updatedProduct.getName());
existingProduct.setPrice(updatedProduct.getPrice());
existingProduct.setQuantity(updatedProduct.getQuantity());
Product savedProduct = productRepository.save(existingProduct);
return ResponseEntity.ok(savedProduct);
    }

    //For deleting a single product from the database
    public ResponseEntity<String> deleteProduct(Long id){
        productRepository.deleteById(id);
        return ResponseEntity.ok(" product deleted  successfully!");
    }





}
