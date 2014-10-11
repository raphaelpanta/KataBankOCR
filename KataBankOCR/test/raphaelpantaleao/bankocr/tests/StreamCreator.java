package raphaelpantaleao.bankocr.tests;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StreamCreator {

	public static InputStream createStreamOf(String[] strings) {
		StringBuilder builder = new StringBuilder();
		for (String string : strings) {
			builder.append(string);
		}
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				builder.toString().getBytes());
		return byteArrayInputStream;
	}

	public static InputStream createAStreamWith(String... strings) {
		return createStreamOf(strings);
	}
}
