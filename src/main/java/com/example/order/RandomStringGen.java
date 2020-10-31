package com.example.order;

import java.util.Random;

public class RandomStringGen {
	private static final String CHAR_LIST = "1234567890-qwertyuiopasdfghjklzxcvbnm=QAZWSXEDCRFVTGBYHNUJMIKLOP";
	private static final int STR_LENGTH = 16;

	public static String generateRandomString() {
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < STR_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	private static int getRandomNumber() {
		int randomInt = new Random().nextInt(CHAR_LIST.length());
		return randomInt < 0 ? randomInt*(-1) : randomInt;
	}
}
