package com.scrap.product.api.config;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.google.common.collect.ImmutableList;

@Configuration
public class MongoConfig {

	/**
	 * Show to Spring that we have two types of conversions deployed to use whenever needed.
	 *
	 * @return
	 */
	@Bean
	public MongoCustomConversions customConversions() {

		return new MongoCustomConversions(ImmutableList.of(DateToOffsetDateTimeConverter.INSTANCE, OffsetDateTimeToDateConverter.INSTANCE));
	}

	/**
	 * Show to the Mongo driver how to convert {@link Date} to {@link OffsetDateTime}. Otherwise, it will not convert by itself.
	 */
	@ReadingConverter
	private static final class DateToOffsetDateTimeConverter implements Converter<Date, OffsetDateTime> {

		public static final DateToOffsetDateTimeConverter INSTANCE = new DateToOffsetDateTimeConverter();

		private DateToOffsetDateTimeConverter() {

		}

		@Override
		public OffsetDateTime convert(final Date source) {

			return source != null ? OffsetDateTime.ofInstant(source.toInstant(), ZoneOffset.UTC) : null;
		}
	}

	/**
	 * Show to the Mongo driver how to convert {@link OffsetDateTime} to {@link Date}. Otherwise, it will not convert by itself.
	 */
	@WritingConverter
	private static final class OffsetDateTimeToDateConverter implements Converter<OffsetDateTime, Date> {

		public static final OffsetDateTimeToDateConverter INSTANCE = new OffsetDateTimeToDateConverter();

		private OffsetDateTimeToDateConverter() {

		}

		@Override
		public Date convert(final OffsetDateTime source) {

			if (source == null) {
				return null;
			}
			return Date.from(source.toInstant());
		}
	}

}
