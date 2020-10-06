package hideandseek.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hideandseek.objects.PositionInfo;

class PositionInfoTest {

	@Test
	void createPlayerTest() {
		String location = "123";
		String green = "12";
		String yellow = "4";
		String red = "7";

		PositionInfo position = new PositionInfo(location, yellow, green, red);
		assertEquals(location, position.getLocation());
		assertEquals(Integer.valueOf(yellow), position.getYellow());
		assertEquals(Integer.valueOf(green), position.getGreen());
		assertEquals(Integer.valueOf(red), position.getRed());
	}
}
