package com.github.raphaelpanta.katabankocr.api;

@FunctionalInterface
public interface ErrorHandlerListener {

	public void onError(Exception e);
}
