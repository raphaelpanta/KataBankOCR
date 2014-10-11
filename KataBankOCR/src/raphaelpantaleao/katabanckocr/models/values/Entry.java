package raphaelpantaleao.katabanckocr.models.values;


public class Entry {
	public final String entry;

	Entry(String entry) {
		this.entry = entry;
	}

	public static EntryBuilder create() {
		return new EntryBuilder();
	}

}
