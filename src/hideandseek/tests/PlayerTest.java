package hideandseek.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hideandseek.objects.Player;

class PlayerTest {

	@Test
	void createPlayerTest() {
		String name = "Jake";
		String colour = "yellow";

		Player player = new Player(name, colour);
		assertEquals(name, player.getName());
		assertEquals(colour, player.getColour());
	}
}
