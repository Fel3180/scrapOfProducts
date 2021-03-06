package com.scrap.product.api.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mongodb.MongoException;
import com.scrap.product.api.domain.model.Product;
import com.scrap.product.api.repository.ProductRepository;
import com.scrap.product.api.vo.ProductResponseVo;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	private static final String URL = "https://www.amazon.com.br/Xiaomi-Vers%C3%A3o-Global-Lacrada-preta/dp/B07V822TVL";

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	void whenAnErrorOccursMustReturnNull() {

		doThrow(MongoException.class).when(productRepository).findProductByUrl(URL);
		final ProductResponseVo productResponseVo = productService.processProduct(URL);
		assertThat(productResponseVo, nullValue());
	}

	@Test
	void shouldAlwaysReturnTheDataRetrievedFromTheDatabase() {

		final Instant createdDate = Instant.parse("2021-02-13T15:12:35Z");
		final Product product = new Product("title", "image", BigDecimal.TEN, "description", URL, createdDate);

		doReturn(product).when(productRepository).findProductByUrl(URL);
		final ProductResponseVo productResponseVo = productService.processProduct(URL);

		assertThat(product.getTitle(), is(productResponseVo.getTitle()));
		assertThat(product.getDescription(), is(productResponseVo.getDescription()));
		assertThat(product.getImage(), is(productResponseVo.getImage()));
		assertThat(product.getUrl(), is(productResponseVo.getUrl()));
		assertThat(product.getPrice(), is(productResponseVo.getPrice()));
	}

	@Test
	void mustCollectTheDataAgainIfThereIsNoRecordInDatabase() {

		final Instant createdDate = Instant.parse("2021-02-13T15:12:35Z");
		final Product product = new Product("title", "image", BigDecimal.TEN, "description", URL, createdDate);
		final ProductResponseVo productResponseVo = new ProductResponseVo("title", "image", BigDecimal.TEN, "description", URL);

		doReturn(null).when(productRepository).findProductByUrl(URL);
		doReturn(product).when(productRepository).save(any());
		final ProductResponseVo productResponseVoResult = productService.processProduct(URL);

		assertThat(productResponseVo.getTitle(), is(productResponseVoResult.getTitle()));
		assertThat(productResponseVo.getDescription(), is(productResponseVoResult.getDescription()));
		assertThat(productResponseVo.getImage(), is(productResponseVoResult.getImage()));
		assertThat(productResponseVo.getUrl(), is(productResponseVoResult.getUrl()));
		assertThat(productResponseVo.getPrice(), is(productResponseVoResult.getPrice()));
	}
}
