package hideandseek.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import hideandseek.HideAndSeek;
import hideandseek.objects.GameState;
import hideandseek.objects.HSMap;
import hideandseek.objects.Player;
import hideandseek.objects.PositionInfo;
import hideandseek.objects.StateOfGame;
import hideandseek.objects.Status;
import hideandseek.objects.Ticket;

@TestMethodOrder(OrderAnnotation.class)
class HideAndSeekTest {
	static String firstSession;
	static String secondSession;
	static String thirdSession;
	
	@BeforeAll
	static void setUp() {
		HideAndSeek.development = true;
	}

	@Test
	@Order(1)
	void fetchMapsTest() throws IOException {
		Status opStatus = HideAndSeek.fetchMaps();

		assertEquals(Status.OKAY, opStatus);
		assertEquals(Status.OKAY.getMsg(), "Okay");

		List<HSMap> maps = HideAndSeek.getMaps();
		List<String> miniMapRounds = Arrays.asList("3", "8");
		List<String> lesleyRounds = Arrays.asList("5", "10");

		assertEquals("Mini_Map", maps.get(0).getName());
		assertEquals(miniMapRounds, maps.get(0).getRounds());

		assertEquals("Lesley", maps.get(1).getName());
		assertEquals(lesleyRounds, maps.get(1).getRounds());
		
		assertEquals(2, HideAndSeek.getMapCount());
	}
	
	@Test
	@Order(2)
	void createGameTest() throws IOException {
		Status opStatus = HideAndSeek.createGame("Kevin", 0, 0);

		assertEquals(Status.OKAY, opStatus);
		assertEquals(Status.OKAY.getMsg(), "Okay");
		
		assertNotNull(HideAndSeek.getSessionID());
		assertNotNull(HideAndSeek.getGameID());
		
		firstSession = HideAndSeek.getSessionID();
	}
	
	@Test
	@Order(3)
	void joinGameTest() throws IOException {
		HideAndSeek.joinGame("Parker", HideAndSeek.getGameID());
		assertNotEquals(firstSession, HideAndSeek.getSessionID());
		secondSession = HideAndSeek.getSessionID();
		
		HideAndSeek.joinGame("Hollywood", HideAndSeek.getGameID());
		
		assertNotEquals(firstSession, HideAndSeek.getSessionID());
		assertNotEquals(secondSession, HideAndSeek.getSessionID());
		thirdSession = HideAndSeek.getSessionID();
	}
	
	@Test
	@Order(4)
	void fetchPlayersTest() throws IOException {
		HideAndSeek.fetchPlayers();

		assertEquals(3, HideAndSeek.getPlayerCount());
		List<Player> players = HideAndSeek.getPlayers();

		assertEquals("Kevin", players.get(0).getName());
		assertEquals("Parker", players.get(1).getName());
		assertEquals("Hollywood", players.get(2).getName());

		assertNotNull(players.get(0).getColour());
		assertNotEquals("", players.get(0).getColour());

		assertNotNull(players.get(1).getColour());
		assertNotEquals("", players.get(1).getColour());

		assertNotNull(players.get(2).getColour());
		assertNotEquals("", players.get(2).getColour());
	}
	
	@Test
	@Order(5)
	void fetchGameStateTest() throws IOException {
		HideAndSeek.fetchGameState();
		GameState state = HideAndSeek.getGameState();

		assertEquals(StateOfGame.OPEN, state.getState());
		assertEquals(0, state.getRound());
		assertEquals("", state.getMsg());
	}

	@Test
	@Order(6)
	void startGameTest() throws IOException {
		Status startThird = HideAndSeek.startGame();
		assertNotEquals(Status.OKAY, startThird);

		HideAndSeek.setSessionID(firstSession);
		Status startFirst = HideAndSeek.startGame();
		assertEquals(Status.OKAY, startFirst);
		
		HideAndSeek.fetchGameState();
		GameState state = HideAndSeek.getGameState();
		assertNotEquals(StateOfGame.OPEN, state.getState());
		assertNotEquals(StateOfGame.OVER, state.getState());
		assertEquals(1, state.getRound());
		assertEquals("", state.getMsg());
	}
	
	@Test
	@Order(7)
	void fetchPositionTest() throws IOException {
		Status status = HideAndSeek.fetchPosition();
		assertEquals(Status.OKAY, status);
		
		PositionInfo position = HideAndSeek.getPosition();
		
		try {
			Integer.parseInt(position.getLocation());
			
			System.out.println(position.getGreen());
			System.out.println(position.getYellow());
			System.out.println(position.getRed());

		} catch (NumberFormatException e) {
			e.printStackTrace();
			fail("Location is not numeric");
		}
	}
	@Test
	@Order(8)
	void makeMoveTest() throws IOException {
		Status status = HideAndSeek.makeMove(2, Ticket.GREEN);
		assertEquals(Status.FINISHED, status);
	}
}
