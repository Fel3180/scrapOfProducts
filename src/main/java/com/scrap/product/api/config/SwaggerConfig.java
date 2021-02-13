package com.scrap.product.api.config;

import java.time.LocalDate;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.ImmutableList;
import com.scrap.product.api.helpers.ApiConstants;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:version.properties")
public class SwaggerConfig {

	@Value("${swaggerBaseUrl}")
	private String baseUrl;

	@Bean
	public Docket productApi() {

		final ResponseMessage unauthorizedResponse = buildResponseMessage(HttpStatus.UNAUTHORIZED, ApiConstants.UNAUTHORIZED_ACCESS);

		final ResponseMessage forbiddenResponse = buildResponseMessage(HttpStatus.FORBIDDEN, ApiConstants.ACCESS_DENIED);

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metaData("1.0")).useDefaultResponseMessages(false).host(baseUrl)
				.groupName(ApiConstants.API_VERSION_1_LABEL).select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.paths(PathSelectors.any()).build()
				.globalResponseMessage(RequestMethod.POST, ImmutableList.of(unauthorizedResponse, forbiddenResponse));
	}

	private ResponseMessage buildResponseMessage(final HttpStatus unauthorized, final String s) {

		return new ResponseMessageBuilder().code(unauthorized.value()).message(s).build();
	}

	private ApiInfo metaData(final String version) {

		final int currentYear = LocalDate.now().getYear();
		return new ApiInfoBuilder().title(ApiConstants.API_TITLE).description(ApiConstants.API_DESCRIPTION).version(version)
				.license(ApiConstants.API_LICENSE + currentYear).build();
	}

	@Bean
	public UiConfiguration uiConfig() {

		return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl(Strings.EMPTY).build();
	}

}
