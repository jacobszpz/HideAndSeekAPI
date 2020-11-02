package hideandseek.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hideandseek.objects.StateOfGame;

class StateOfGameTest {

	@Test
	void createStateTest() {
		assertEquals(StateOfGame.OPEN, StateOfGame.fromString("OPEN"));
		assertEquals(StateOfGame.FUGITIVE, StateOfGame.fromString("FUGITIVE"));
		assertEquals(StateOfGame.DETECTIVE, StateOfGame.fromString("DETECTIVE"));
		assertEquals(StateOfGame.OVER, StateOfGame.fromString("OVER"));
		assertEquals(StateOfGame.OPEN, StateOfGame.fromString("SOMETHING"));
	}
	

	@Test
	void toStringTest() {
		assertEquals("OPEN", StateOfGame.toString(StateOfGame.OPEN));
		assertEquals("FUGITIVE", StateOfGame.toString(StateOfGame.FUGITIVE));
		assertEquals("DETECTIVE", StateOfGame.toString(StateOfGame.DETECTIVE));
		assertEquals("OVER", StateOfGame.toString(StateOfGame.OVER));
		assertEquals("OPEN", StateOfGame.toString(StateOfGame.OPEN));
	}

}
