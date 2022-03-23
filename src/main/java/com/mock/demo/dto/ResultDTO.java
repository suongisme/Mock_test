package com.mock.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author SuongNguyen
 * @created 3/23/2022
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {

    private Object data;
    private HttpStatus status;
    private String message;

    public ResultDTO(HttpStatus status) {
        this(null, status, null);
    }
}
