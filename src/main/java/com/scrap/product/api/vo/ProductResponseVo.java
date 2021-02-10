package com.scrap.product.api.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ProductResponse")
public class ProductResponseVo {

	@JsonProperty
	@ApiModelProperty(position = 0, notes = "title", example = "Notebook Dell Vostro")
	private String title;

	@JsonProperty
	@ApiModelProperty(position = 1, notes = "image", example = "wwww.amazon.com/dell.png")
	private String image;

	@JsonProperty
	@ApiModelProperty(position = 2, notes = "price", example = "3.456,99")
	private BigDecimal price;

	@JsonProperty
	@ApiModelProperty(position = 3, notes = "description", example = "Notebook with 4G HD SSD256")
	private String description;

	@JsonProperty
	@ApiModelProperty(position = 4, notes = "url", example = "wwww.amazon.com/dell-vostro")
	private String url;

	public ProductResponseVo() {
		super();
	}

	public ProductResponseVo(String title, String image, BigDecimal price, String description, String url) {

		this.title = title;
		this.image = image;
		this.price = price;
		this.description = description;
		this.url = url;
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
