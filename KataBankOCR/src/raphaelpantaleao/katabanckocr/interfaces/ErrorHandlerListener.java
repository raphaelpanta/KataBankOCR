package raphaelpantaleao.katabanckocr.interfaces;

@FunctionalInterface
public interface ErrorHandlerListener {

	public void onError(Exception e);
}
