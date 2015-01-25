package raphaelpantaleao.katabanckocr.modules;

import raphaelpantaleao.katabanckocr.models.DocumentProcessor;
import raphaelpantaleao.katabanckocr.models.EntryExtractor;
import raphaelpantaleao.katabanckocr.models.EntryValidator;

import com.google.inject.AbstractModule;

public class BackOCRModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(DocumentProcessor.class).asEagerSingleton();
		bind(EntryExtractor.class).asEagerSingleton();
		bind(EntryValidator.class).asEagerSingleton();
	}

}
