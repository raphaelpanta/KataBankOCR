package raphaelpantaleao.bankocr.tests;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StreamCreator {

	public static InputStream createStreamOf(String[] strings) {
		byte[] stringInBytes = asList(strings).stream().collect(joining()).getBytes();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				stringInBytes);
		return byteArrayInputStream;
	}

	public static InputStream createAStreamWith(String... strings) {
		return createStreamOf(strings);
	}
}
