package com.scrap.product.api.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import com.scrap.product.api.domain.model.Product;

@Configuration
@DependsOn("mongoTemplate")
public class IndexConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexConfig.class);

	private static final Long AFTER_EXPIRE_SECONDS = 3600L;

	@Value("${spring.data.mongodb.database}")
	private String database;

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostConstruct
	public void configureIndexes() {

		try {
			if (!mongoTemplate.collectionExists(Product.class)) {
				mongoTemplate.indexOps(Product.class)
						.ensureIndex(new Index().on("createdDate", Sort.Direction.ASC).expire(AFTER_EXPIRE_SECONDS));
			}
		} catch (final Exception e) {
			LOGGER.debug(e.getMessage(), e);
		}
		LOGGER.debug("Index creation finished");
	}
}
