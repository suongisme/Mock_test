package com.mock.demo.controller;

import com.mock.demo.dto.ResultDTO;
import com.mock.demo.entity.Product;
import com.mock.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author SuongNguyen
 * @created 3/23/2022
 */

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity findProductAll() {
        ResultDTO result = this.productService.findProductAll();
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping("/{productCode}")
    public ResponseEntity findProductByCode(@PathVariable String productCode) {
        ResultDTO result = this.productService.findProductByCode(productCode);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product) {
        ResultDTO result = this.productService.save(product);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PutMapping("/{productCode}")
    public ResponseEntity updateProduct(@PathVariable String productCode, @RequestBody Product product) {
        ResultDTO result = this.productService.update(productCode, product);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{productCode}")
    public ResponseEntity deleteProduct(@PathVariable String productCode) {
        ResultDTO result = this.productService.delete(productCode);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
