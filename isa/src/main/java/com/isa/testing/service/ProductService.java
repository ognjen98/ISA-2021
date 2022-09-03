package com.isa.testing.service;

import com.isa.testing.domain.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    void delete(long id);

    Product findById(long id);

    List<Product> findAll();

    Product update(Product product) throws Exception;

}
