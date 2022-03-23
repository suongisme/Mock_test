package com.mock.demo.service;

import com.mock.demo.dto.ResultDTO;
import com.mock.demo.entity.Product;
import com.mock.demo.mapper.ProductMapper;
import com.mock.demo.utils.CreateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author SuongNguyen
 * @created 3/23/2022
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductMapper productMapper;

    public ResultDTO findProductAll() {
        try {
            List<Product> products = this.productMapper.findProductAll();
            log.info("found list of product: {}", products);
            return new ResultDTO(products, HttpStatus.OK, "");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResultDTO(null, HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public ResultDTO findProductByCode(String code) {
        try {
            if (Objects.isNull(code)) throw new RuntimeException("ID is required");

            Optional<Product> productOPT = this.productMapper.findByCode(code);
            Product product = productOPT.orElseThrow(() -> new RuntimeException("Not found product: " + code));
            log.info("Found Product: {}", product);
            return new ResultDTO(product, HttpStatus.OK, "");

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResultDTO(null, HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public ResultDTO save(Product product) {
        try {

            if (Objects.isNull(product)) throw new RuntimeException("");

            product.setCreated(Instant.now());
            product.setCode(CreateUtils.generateCode(Product.class));

            int insert = this.productMapper.insert(product);
            log.info("Insert product successfully: {}", insert);
            return new ResultDTO(product, HttpStatus.OK, "Insert product successfully");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResultDTO(null, HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public ResultDTO update(String code, Product product) {
        try {

            Optional<Product> productOpt = this.productMapper.findByCode(product.getCode());
            productOpt.orElseThrow(() -> new RuntimeException("Not found product: " + code));

            product.setCode(code);
            int update = this.productMapper.update(product);
            log.info("Update product successfully: {}", update);
            return new ResultDTO(product, HttpStatus.OK, "Update product successfully");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResultDTO(null, HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public ResultDTO delete(String code) {
        try {

            if (Objects.isNull(code)) throw new RuntimeException("Code is required");
            ResultDTO resultFind = this.findProductByCode(code);
            if (!HttpStatus.OK.equals(resultFind.getStatus())) {
                return resultFind;
            }

            this.productMapper.deleteByCode(code);
            log.info("Delete Product successfully: {}", code);
            return new ResultDTO(null, HttpStatus.OK, "Delete Product successfully");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResultDTO(null, HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
