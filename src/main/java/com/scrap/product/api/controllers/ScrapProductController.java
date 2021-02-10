package com.scrap.product.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = { "Scrap of products"})
public class ScrapProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapProductController.class);

	@ApiOperation(value = "Scrap of products", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not found") })
	@PutMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<String> put(
			@ApiParam(value = "URL to get details of products", required = true) @RequestParam(name = "url") final String url) {

		LOGGER.debug("Received GET request");
		return ResponseEntity.ok("String");
	}

	@ApiOperation(value = "get products", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not found") })
	@GetMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<String> get(
			@ApiParam(value = "URL to get details of products", required = true) @RequestParam(name = "url") final String url) {

		LOGGER.debug("Received GET request");
		return ResponseEntity.ok("String");
	}
}
