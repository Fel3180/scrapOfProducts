package com.scrap.product.api.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

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

		Product productPersisted = productRepository.findProductByUrl(url);

		if (Objects.nonNull(productPersisted)) {
			return new ProductResponseVo(productPersisted.getTitle(), productPersisted.getImage(), productPersisted.getPrice(),
					productPersisted.getDescription(), productPersisted.getUrl());
		} else {
			try {
				final Document doc = Jsoup.connect(url).timeout(TIMEOUT).get();

				final Instant createdDate = Instant.now();

				String title = null;
				String image = null;
				for (final Element element : doc.select("div")) {
					if (doc.title() == null) {
						title = element.select("[title]").text();
						System.out.println(element.select("[title]").text());
					} else {
						title = doc.title();
					}

					image = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]").get(0).baseUri();
				}

				productPersisted = productRepository.save(new Product(title, image, BigDecimal.TEN, "felipe", url, createdDate));

				return new ProductResponseVo(productPersisted.getTitle(), productPersisted.getImage(), productPersisted.getPrice(),
						productPersisted.getDescription(), productPersisted.getUrl());
			} catch (final HttpStatusCodeException | IOException e) {
				LOGGER.debug("ERROR", e);
				return null;
			}
		}
	}
}
