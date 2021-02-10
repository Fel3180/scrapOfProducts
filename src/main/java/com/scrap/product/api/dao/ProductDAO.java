package com.scrap.product.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scrap.product.api.domain.model.Product;

public interface ProductDAO extends MongoRepository<Product, String> {

}
