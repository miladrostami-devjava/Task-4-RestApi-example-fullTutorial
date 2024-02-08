package com.boot.demo.controller;


import com.boot.demo.entity.Product;
import com.boot.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pro")
public class ProductController {
private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //for post method, create a new product
    @PostMapping("/product")
    public ResponseEntity<Product> savedProduct(@RequestBody Product product){
Product newProduct  = savedProduct(product).getBody();
return ResponseEntity.ok(newProduct);
    }
    //For Get Request all the products
    public List<Product> getAllProducts(){
        return productService.fetchAllProducts().getBody();
    }

    //For Get Request of a single product
    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id){
 Optional<Product> product = productService.fetchProductById(id);
if (product != null){
    return ResponseEntity.ok(product);
}else {
    return ResponseEntity.notFound().build();
}
    }
    //For Update Requests
    @PutMapping("/product/{productId}")
    public ResponseEntity<Product> updatedProduct(@PathVariable (value = "productId") Long productId,
                                                  @RequestBody Product product){
        return productService.updateProduct(productId,product);
    }
    //For Delete Requests
    @DeleteMapping(value = "/product/{productId}")
    public String deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return "product deleted successfully and against id ," + productId;
    }






}
