package com.scrap.product.api.exceptions;

import java.util.IllegalFormatException;

import org.apache.logging.log4j.util.Strings;

public enum IssueEnum {

	BAD_REQUEST(1, "Malformed Request"), //
	UNEXPECTED_ERROR(2, "Unexpected error."); //

	private final int code;
	private final String message;

	IssueEnum(final int code, final String message) {

		this.code = code;
		this.message = message;
	}

	public int getCode() {

		return code;
	}

	public String getFormattedMessage(final Object... args) {

		if (message == null) {
			return Strings.EMPTY;
		}

		try {
			return String.format(message, args);
		} catch (final IllegalFormatException e) {
			return message.replace("%s", "");
		}
	}
}
