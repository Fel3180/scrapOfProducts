package com.scrap.product.api.controllers;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.scrap.product.api.services.ProductService;
import com.scrap.product.api.vo.ProductResponseVo;

@SpringBootTest
@ActiveProfiles("test")
class ProductControllerIntegrationTest {

	private static final String URL_AMAZON = "https://www.amazon.com.br/Xiaomi-Vers%C3%A3o-Global-Lacrada-preta/dp/B07V822TVL";

	@MockBean
	private ProductService productService;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws ServletException {

		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void getProductValidUrl() throws Exception {

		doReturn(new ProductResponseVo("TEST", "IMAGE", BigDecimal.TEN, "TEST", URL_AMAZON)).when(productService)
				.processProduct(URL_AMAZON);
		mockMvc.perform(get("/v1").queryParam("url", URL_AMAZON)).andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.title").value("TEST")).andExpect(jsonPath("$.image").value("IMAGE"))
				.andExpect(jsonPath("$.price").value(BigDecimal.TEN)).andExpect(jsonPath("$.description").value("TEST"))
				.andExpect(jsonPath("$.url").value(URL_AMAZON));
	}

	@Test
	void shouldReturnErrorWhenUrlNotSent() throws Exception {

		mockMvc.perform(get("/v1")).andExpect(status().isBadRequest());
	}

	@Test
	void shouldReturnErrorWhenSentUrlEmpty() throws Exception {

		mockMvc.perform(get("/v1").queryParam("url", "")).andExpect(status().isBadRequest());
	}

	@Test
	void shouldReturnErrorWhenSentInvalidUrl() throws Exception {

		mockMvc.perform(get("/v1").queryParam("url", "wwww")).andExpect(status().isBadRequest());
	}
}
