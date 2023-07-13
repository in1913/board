package board;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomPassword {

	public static String getRandomPassword(int size) {
		String result = RandomStringUtils.randomAlphanumeric(size);
		return result;
	}
}
