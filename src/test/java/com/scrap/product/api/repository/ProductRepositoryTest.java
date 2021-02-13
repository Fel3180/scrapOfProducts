package com.scrap.product.api.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import com.scrap.product.api.domain.model.Product;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

	final ArgumentCaptor<Query> argumentCaptureForQuery = ArgumentCaptor.forClass(Query.class);

	@Captor
	private ArgumentCaptor<Map<String, String>> headersCaptor;

	@Mock
	private MongoOperations mongoOperations;

	@InjectMocks
	private ProductRepositoryImpl productRepository;

	@Test
	void testFindProductByUrlSuccessfully() {

		final Product productMock = mock(Product.class);
		doReturn(productMock).when(mongoOperations).findOne(any(), any());
		assertThat(productRepository.findProductByUrl("URL"), is(productMock));
	}

	@Test
	void mustCreateQueryCorrectly() {

		final Product productMock = mock(Product.class);
		final String query = "Query: { \"url\" : \"URL\"}, Fields: {}, Sort: {}";

		doReturn(productMock).when(mongoOperations).findOne(any(), any());
		productRepository.findProductByUrl("URL");
		verify(mongoOperations, times(1)).findOne(argumentCaptureForQuery.capture(), eq(Product.class));
		assertThat(argumentCaptureForQuery.getValue().toString(), is(query));
	}

}
