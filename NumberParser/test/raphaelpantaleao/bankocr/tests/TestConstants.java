package raphaelpantaleao.bankocr.tests;

public class TestConstants {
    public static final String SCANNED_TEXT_123456789 = "    _  _     _  _  _  _  _ \n"
	    + "  | _| _||_||_ |_   ||_||_|\n"
	    + "  ||_  _|  | _||_|  ||_| _|\n"
	    + "                           \n";
    public static final String SCANNED_TEXT_WITH_ERRORS = "   _  _     _  _  _  _  _  \n"
	    + " | _| _||_||_ |_   ||_||_| \n"
	    + " ||_  _|  | _||_|  ||_| _| \n"
	    + "                           \n";
    public static final String ACCOUNT_FOUND_123456789 = "123456789\n";

    public static final String ZEROS = 
	      " _  _  _  _  _  _  _  _  _ \n"
	    + "| || || || || || || || || |\n" 
	    + "|_||_||_||_||_||_||_||_||_|\n"
	    + "                           \n";

    public static final String ONES = "                           \n"
	    + "  |  |  |  |  |  |  |  |  |\n" + "  |  |  |  |  |  |  |  |  |\n"
	    + "                           \n";

    public static final String TWOS = " _  _  _  _  _  _  _  _  _ \n"
	    + " _| _| _| _| _| _| _| _| _|\n" + "|_ |_ |_ |_ |_ |_ |_ |_ |_ \n"
	    + "                           \n";

    public static final String THREES = " _  _  _  _  _  _  _  _  _ \n"
	    + " _| _| _| _| _| _| _| _| _|\n" + " _| _| _| _| _| _| _| _| _|\n"
	    + "                           \n";

    public static final String FOURS = "                           \n"
	    + "|_||_||_||_||_||_||_||_||_|\n" + "  |  |  |  |  |  |  |  |  |\n"
	    + "                           \n";

    public static final String FIVES = " _  _  _  _  _  _  _  _  _ \n"
	    + "|_ |_ |_ |_ |_ |_ |_ |_ |_ \n" + " _| _| _| _| _| _| _| _| _|\n"
	    + "                           \n";

    public static final String SIXS = " _  _  _  _  _  _  _  _  _ \n"
	    + "|_ |_ |_ |_ |_ |_ |_ |_ |_ \n" + "|_||_||_||_||_||_||_||_||_|\n"
	    + "                           \n";

    public static final String SEVENS = " _  _  _  _  _  _  _  _  _ \n"
	    + "  |  |  |  |  |  |  |  |  |\n" + "  |  |  |  |  |  |  |  |  |\n"
	    + "                           \n";

    public static final String EIGHTS = " _  _  _  _  _  _  _  _  _ \n"
	    + "|_||_||_||_||_||_||_||_||_|\n" + "|_||_||_||_||_||_||_||_||_|\n"
	    + "                           \n";

    public static final String NINES = " _  _  _  _  _  _  _  _  _ \n"
	    + "|_||_||_||_||_||_||_||_||_|\n" + " _| _| _| _| _| _| _| _| _|\n"
	    + "                           \n";

    public static final String[] NUMBER_IN_DIGITS = { ZEROS, ONES, TWOS,
	    THREES, FOURS, FIVES, SIXS, SEVENS, EIGHTS, NINES };

    public static final String[] ACCOUNTS_EXPECTED = { "000000000\n",
	    "111111111\n", "222222222\n", "333333333\n", "444444444\n",
	    "555555555\n", "666666666\n", "777777777\n", "888888888\n",
	    "999999999\n" };

}
