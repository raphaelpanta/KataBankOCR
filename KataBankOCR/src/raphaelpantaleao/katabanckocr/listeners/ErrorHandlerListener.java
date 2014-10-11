package raphaelpantaleao.katabanckocr.listeners;

@FunctionalInterface
public interface ErrorHandlerListener {

	public void onError(Exception e);
}
