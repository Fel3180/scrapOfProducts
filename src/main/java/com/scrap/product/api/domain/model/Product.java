package com.scrap.product.api.domain.model;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

	private String title;

	private String image;

	private BigDecimal price;

	private String description;

	private String url;

	private Instant createdDate;

	public Product() {

		super();
	}

	public Product(final String title, final String image, final BigDecimal price, final String description, final String url,
			final Instant createdDate) {

		this.title = title;
		this.image = image;
		this.price = price;
		this.description = description;
		this.url = url;
		this.createdDate = createdDate;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(final String title) {

		this.title = title;
	}

	public String getImage() {

		return image;
	}

	public void setImage(final String image) {

		this.image = image;
	}

	public BigDecimal getPrice() {

		return price;
	}

	public void setPrice(final BigDecimal price) {

		this.price = price;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(final String url) {

		this.url = url;
	}

	public Instant getCreatedDate() {

		return createdDate;
	}

	public void setCreatedDate(final Instant createdDate) {

		this.createdDate = createdDate;
	}
}
