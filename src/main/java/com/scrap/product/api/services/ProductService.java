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

		final Document doc = Jsoup.connect(url).timeout(TIMEOUT).get();

		final Instant createdDate = Instant.now();
		final String price;
		String title = null;
		String img = null;

		BigDecimal priceConverted = BigDecimal.ZERO;

		if (Objects.nonNull(doc)) {

			price = doc.getElementsMatchingOwnText("\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})$").get(0).toString();

			if (price != null) {
				priceConverted = new BigDecimal(price.replaceAll("[^0-9,.]", "").replace(",", "."));
			}

			title = doc.select("html > head > title").text();
			img = doc.select("div:not(#div) > img ").get(0).attr("src");
		}

		return productRepository.save(new Product(title, img, priceConverted, "lorem lorem", url, createdDate));
	}
}
