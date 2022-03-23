package com.mock.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * @author SuongNguyen
 * @created 3/23/2022
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Instant created;
}
