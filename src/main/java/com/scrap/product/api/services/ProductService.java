package com.scrap.product.api.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrap.product.api.domain.model.Product;
import com.scrap.product.api.repository.ProductRepository;
import com.scrap.product.api.vo.ProductResponseVo;

@Service
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	private static final int TIMEOUT = 10000;

	private final ProductRepository productRepository;

	@Autowired
	public ProductService(final ProductRepository productRepository) {

		this.productRepository = productRepository;
	}

	public ProductResponseVo processProduct(final String url) {

		try {
			Product productPersisted = productRepository.findProductByUrl(url);
			if (Objects.isNull(productPersisted)) {
				productPersisted = extractProductDataOnWebSite(url);
			}
			return new ProductResponseVo(productPersisted.getTitle(), productPersisted.getImage(), productPersisted.getPrice(),
					productPersisted.getDescription(), productPersisted.getUrl());
		} catch (final Exception e) {
			LOGGER.debug("Occurred an error", e);
			return null;
		}
	}

	private Product extractProductDataOnWebSite(final String url) throws IOException {

		final Instant createdDate = Instant.now();
		final Document doc = Jsoup.connect(url).timeout(TIMEOUT).get();

		final String title = doc.select("html > head > title").text();
		final String img = doc.select("img[src$=.jpg]").text();

		return productRepository.save(new Product(title, img, BigDecimal.TEN, "felipe", url, createdDate));
	}
}
