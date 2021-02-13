package com.scrap.product.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scrap.product.api.domain.model.Product;

public interface ProductRepository extends MongoRepository<Product, Long>, ProductRepositoryCustom {

}
