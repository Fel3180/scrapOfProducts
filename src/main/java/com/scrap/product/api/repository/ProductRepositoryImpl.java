package com.scrap.product.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.scrap.product.api.domain.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

	private static final String FIELD_URL = "url";
	private final MongoOperations mongoOperations;

	@Autowired
	public ProductRepositoryImpl(final MongoOperations mongoOperations) {

		this.mongoOperations = mongoOperations;
	}

	@Override
	public Product findProductByUrl(final String url) {

		final Query query = new Query();
		query.addCriteria(Criteria.where(FIELD_URL).is(url));
		return mongoOperations.findOne(query, Product.class);
	}
}
