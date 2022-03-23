package com.mock.demo.mapper;

import com.mock.demo.entity.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author SuongNguyen
 * @created 3/23/2022
 */

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM PRODUCT")
    List<Product> findProductAll();

    @Select("SELECT * FROM PRODUCT WHERE CODE = #{code}")
    Optional<Product> findByCode(@Param("code") String code);

    @Transactional
    @Insert("INSERT INTO PRODUCT(name, description, code, created) VALUES(#{name}, #{description}, #{code}, #{created})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Product product);

    @Transactional
    @Update("UPDATE PRODUCT SET NAME = #{name}, DESCRIPTION = #{description} WHERE CODE = #{code}")
    int update(Product product);

    @Transactional
    @Delete("DELETE FROM PRODUCT WHERE CODE = #{code}")
    int deleteByCode(@Param("code") String code);
}
