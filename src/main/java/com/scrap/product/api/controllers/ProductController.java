package com.scrap.product.api.controllers;

import org.hibernate.validator.constraints.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scrap.product.api.services.ProductService;
import com.scrap.product.api.vo.ProductResponseVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@Api(tags = { "Scrap of products" })
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	private final ProductService productService;

	@Autowired
	public ProductController(final ProductService productService) {

		this.productService = productService;
	}

	@ApiOperation(value = "get products", response = ProductResponseVo.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not found") })
	@GetMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponseVo> get(
			@ApiParam(value = "URL to get details of products", required = true) @RequestParam @URL(message = "Please, enter a valid URL.") final String url) {

		LOGGER.debug("Received GET request");
		return ResponseEntity.ok(productService.processProduct(url));
	}
}
