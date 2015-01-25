package raphaelpantaleao.katabanckocr.api;

@FunctionalInterface
public interface ErrorHandlerListener {

	public void onError(Exception e);
}
