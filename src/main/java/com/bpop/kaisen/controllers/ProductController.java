package com.bpop.kaisen.controllers;

import com.bpop.kaisen.models.dto.DataInfoRes;
import com.bpop.kaisen.models.entities.Product;
import com.bpop.kaisen.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
@CrossOrigin(origins = "${project.config.cors.allowed.origins}")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(path = "/createUpdate")
    public ResponseEntity<DataInfoRes> createUpdateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.createAndUpdateProduct(product), HttpStatus.CREATED);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<DataInfoRes> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping(path = "/get/{id}/{action}")
    public ResponseEntity<DataInfoRes> getOrDeleteProductById(@PathVariable Integer id, @PathVariable String action) {
        return new ResponseEntity<>(productService.getOrDeleteProductById(id, action), HttpStatus.OK);
    }

}
