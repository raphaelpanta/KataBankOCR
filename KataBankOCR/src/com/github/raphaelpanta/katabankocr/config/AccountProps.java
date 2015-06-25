package com.github.raphaelpanta.katabankocr.config;

public interface AccountProps {
	int lineLegth();

	int lines();

	public static final class DefaultAccountProps implements AccountProps {
		@Override
		public final int lineLegth() {
			return 9;
		}

		@Override
		public final int lines() {
			return 1;
		}

	}
}
