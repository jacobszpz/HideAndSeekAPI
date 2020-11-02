package hideandseek.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hideandseek.objects.GameState;
import hideandseek.objects.StateOfGame;

class GameStateTest {

	@Test
	void createStateFromStrings() {
		GameState state = new GameState("OPEN", "0", "");
		assertEquals(0, state.getRound());
		assertEquals("", state.getMsg());
		assertEquals(StateOfGame.OPEN, state.getState());
		
		state.setMsg("test");
		state.setRound(2);
		state.setState(StateOfGame.DETECTIVE);
		assertEquals("test", state.getMsg());
		assertEquals(2, state.getRound());
		assertEquals(StateOfGame.DETECTIVE, state.getState());
	}

	@Test
	void createStateFromState() {
		GameState state = new GameState(StateOfGame.FUGITIVE, "12", "test message");
		assertEquals(12, state.getRound());
		assertEquals("test message", state.getMsg());
		assertEquals(StateOfGame.FUGITIVE, state.getState());
		
		state.setMsg("test");
		state.setRound(2);
		state.setState(StateOfGame.DETECTIVE);
		assertEquals("test", state.getMsg());
		assertEquals(2, state.getRound());
		assertEquals(StateOfGame.DETECTIVE, state.getState());
	}
}
