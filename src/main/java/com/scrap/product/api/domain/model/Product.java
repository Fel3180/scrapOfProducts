package com.scrap.product.api.domain.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

public class Product {

	@Id
	private String id;

	private String title;

	private String image;

	private BigDecimal price;

	private String description;

	private String url;

	public Product() {

		super();
	}

	public Product(String id, String title, String image, BigDecimal price, String description, String url) {

		this.id = id;
		this.title = title;
		this.image = image;
		this.price = price;
		this.description = description;
		this.url = url;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getImage() {

		return image;
	}

	public void setImage(String image) {

		this.image = image;
	}

	public BigDecimal getPrice() {

		return price;
	}

	public void setPrice(BigDecimal price) {

		this.price = price;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}
}
