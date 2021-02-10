package com.scrap.product.api.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrap.product.api.dao.ProductDAO;
import com.scrap.product.api.domain.model.Product;
import com.scrap.product.api.vo.ProductResponseVo;

@Service
public class ProductService {

	private final ProductDAO productRepository;

	@Autowired
	public ProductService(final ProductDAO productRepository) {

		this.productRepository = productRepository;
	}

	public ProductResponseVo processProduct(final String url) {

		final Product product = new Product("1", "teste", "teste", BigDecimal.TEN, "felipe", "www.teste.com");
		productRepository.save(product);
		return null;
	}
}
