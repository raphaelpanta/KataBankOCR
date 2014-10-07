package raphaelpantaleao.bankocr.tests.endtoend;

public class TestConstants {
	public static final String SCANNED_TEXT_123456789 = "    _  _     _  _  _  _  _ \n"
			+ "  | _| _||_||_ |_   ||_||_|\n"
			+ "  ||_  _|  | _||_|  ||_| _|\n"
			+ "                           \n";
	public static final String ACCOUNT_FOUND_123456789 = "123456789\n";

	public static final String ZEROS = " _  _  _  _  _  _  _  _  _ \n"
			+ "| || || || || || || || || |\n" + "|_||_||_||_||_||_||_||_||_|\n"
			+ "                           \n";

	private static final String ONES = "                           \n"
			+ "  |  |  |  |  |  |  |  |  |\n" + "  |  |  |  |  |  |  |  |  |\n"
			+ "                           \n";

	public static final String[] NUMBER_IN_DIGITS = { ZEROS, ONES };
	public static final String[] ACCOUNTS_EXPECTED = { "000000000\n",
			"111111111\n" };
}
