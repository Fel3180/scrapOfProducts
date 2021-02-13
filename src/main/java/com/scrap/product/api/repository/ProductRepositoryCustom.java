package com.scrap.product.api.repository;

import com.scrap.product.api.domain.model.Product;

public interface ProductRepositoryCustom {

	Product findProductByUrl(final String url);
}
