package com.github.raphaelpanta.katabankocr.config;

public interface ScannerProps {
	int lineLength();

	int lines();

	public static final class DefaultScannerProps implements ScannerProps {
		@Override
		public final int lineLength() {
			return 27;
		}

		@Override
		public final int lines() {
			return 4;
		}
	}
}
