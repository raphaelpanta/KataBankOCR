package com.github.raphaelpanta.katabankocr.api;

import java.io.InputStream;

import com.github.raphaelpanta.katabankocr.exceptions.StreamProviderException;

public interface StreamProvider {

	InputStream getStream() throws StreamProviderException;
}
