package hideandseek;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hideandseek.objects.Player;
import hideandseek.objects.PositionInfo;
import hideandseek.objects.StateOfGame;
import hideandseek.objects.GameState;
import hideandseek.objects.HSMap;
import hideandseek.objects.InGamePlayer;
import hideandseek.objects.Status;
import hideandseek.objects.Ticket;
import hideandseek.util.AIHttp;
import hideandseek.util.CsvParsingException;
import hideandseek.util.CsvUtil;
import hideandseek.util.ParameterStringBuilder;

public class HideAndSeek {
	private static String apiURL = "http://challenge.uclan.ac.uk:8080/preston/";
	private static String sessionID = "";
	private static String gameID = "";

	private static final String teamName = "6B-StudioSix";
	private static List<HSMap> mapList;
	private static List<Player> playerList;
	private static List<InGamePlayer> playerDetails;
	private static List<Ticket> drXLog;
	private static GameState gameState;
	private static PositionInfo position;
	private static boolean createdGame = false;
	
	public static boolean development = false;

	public static List<HSMap> getMaps() {
		return mapList;
	}
	
	public static int getMapCount() {
		return mapList.size();
	}

	public static List<Player> getPlayers() {
		return playerList;
	}
	
	public static List<InGamePlayer> getPlayerDetails() {
		return playerDetails;
	}
	
	public static int getPlayerDetailsCount() {
		return playerDetails.size();
	}
	
	public static int getPlayerCount() {
		return playerList.size();
	}
	
	public static List<Ticket> getDrXLog(){
		return drXLog;
	}
	
	public static int getDrXLogCount() {
		return drXLog.size();
	}
	
	public static Status fetchMaps() {
		Status operationStatus = Status.OKAY;
		List<HSMap> tempList = new ArrayList<HSMap>();

		try {
			List<List<String>> csvTable = makeAPIRequest("getMaps");

			List<String> statusRow = csvTable.get(0);
			operationStatus = Status.fromString(statusRow.get(0));
			operationStatus.setMsg(statusRow.get(1));

			if (operationStatus == Status.OKAY) {
				for (int i = 1; i < csvTable.size(); i++) {
					tempList.add(new HSMap(csvTable.get(i)));
				}
			}
			
			mapList = tempList;
		} catch (CsvParsingException e) {
			e.printStackTrace();
			operationStatus = Status.CSV_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			operationStatus = Status.IO_ERROR;
		}

		return operationStatus;
	}

	public static Status createGame(String playerName, int mapIndex, int roundsIndex) {
		Status operationStatus = Status.OKAY;
		HSMap selectedMap = mapList.get(mapIndex);
		String rounds = selectedMap.getRounds().get(roundsIndex);

		try {
			Map<String, String> parameters = new HashMap<>();
			parameters.put("playerName", playerName);
			parameters.put("numRounds", rounds);
			parameters.put("appID", teamName);
			parameters.put("mapName", selectedMap.getName());

			List<List<String>> csvTable = makeAPIRequest("createGame", parameters);
			
			List<String> statusRow = csvTable.get(0);
			operationStatus = Status.fromString(statusRow.get(0));
			operationStatus.setMsg(statusRow.get(1));

			if (operationStatus == Status.OKAY) {
				List<String> dataRow = csvTable.get(1);
				setSessionID(dataRow.get(0));
				setGameID(dataRow.get(1));
				createdGame = true;
			}
		} catch (CsvParsingException e) {
			e.printStackTrace();
			operationStatus = Status.CSV_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			operationStatus = Status.IO_ERROR;
		}

		return operationStatus;
	}

	public static Status joinGame(String playerName, String gameID) {
		Status operationStatus = Status.OKAY;

		try {
			Map<String, String> parameters = new HashMap<>();
			parameters.put("playerName", playerName);
			parameters.put("appID", teamName);
			parameters.put("gameID", gameID);

			List<List<String>> csvTable = makeAPIRequest("joinGame", parameters);
			
			List<String> statusRow = csvTable.get(0);
			operationStatus = Status.fromString(statusRow.get(0));
			operationStatus.setMsg(statusRow.get(1));

			if (operationStatus == Status.OKAY) {
				List<String> dataRow = csvTable.get(1);
				setSessionID(dataRow.get(0));
				setGameID(gameID);
			}
		} catch (CsvParsingException e) {
			e.printStackTrace();
			operationStatus = Status.CSV_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			operationStatus = Status.IO_ERROR;
		}

		return operationStatus;
	}

	public static Status fetchPlayers() {
		Status operationStatus = Status.OKAY;
		List<Player> tempList = new ArrayList<Player>();

		try {
			Map<String, String> parameters = new HashMap<>();
			parameters.put("gameID", getGameID());
			List<List<String>> csvTable = makeAPIRequest("getPlayers", parameters);

			List<String> statusRow = csvTable.get(0);
			operationStatus = Status.fromString(statusRow.get(0));
			operationStatus.setMsg(statusRow.get(1));

			if (operationStatus == Status.OKAY) {
				for (int i = 1; i < csvTable.size(); i++) {
					List<String> playerRow = csvTable.get(i);
					tempList.add(new Player(playerRow.get(0), playerRow.get(1)));
				}
			}
			
			playerList = tempList;
		} catch (CsvParsingException e) {
			e.printStackTrace();
			operationStatus = Status.CSV_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			operationStatus = Status.IO_ERROR;
		}

		return operationStatus;
	}

	public static Status startGame() {
		Status operationStatus = Status.NOT_POSSIBLE;

		if (createdGame) {
			operationStatus = Status.OKAY;

			try {
				Map<String, String> parameters = new HashMap<>();
				parameters.put("gameID", gameID);
	
				List<List<String>> csvTable = makeAPIRequest("startGame", parameters, true);
	
				List<String> statusRow = csvTable.get(0);
				operationStatus = Status.fromString(statusRow.get(0));
				operationStatus.setMsg(statusRow.get(1));
	
			} catch (CsvParsingException e) {
				e.printStackTrace();
				operationStatus = Status.CSV_ERROR;
			} catch (IOException e) {
				e.printStackTrace();
				operationStatus = Status.IO_ERROR;
			}
		}

		return operationStatus;
	}

	public static Status makeMove(int destination, Ticket ticket) {
		Status operationStatus = Status.OKAY;

		try {
			Map<String, String> parameters = new HashMap<>();
			parameters.put("destination", Integer.toString(destination));
			parameters.put("ticket", Ticket.toString(ticket));

			List<List<String>> csvTable = makeAPIRequest("makeMove", parameters, true);

			List<String> statusRow = csvTable.get(0);
			operationStatus = Status.fromString(statusRow.get(0));
			operationStatus.setMsg(statusRow.get(1));

		} catch (CsvParsingException e) {
			e.printStackTrace();
			operationStatus = Status.CSV_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			operationStatus = Status.IO_ERROR;
		}

		return operationStatus;
	}

	public static Status fetchPosition() {
		Status operationStatus = Status.OKAY;

		try {
			List<List<String>> csvTable = makeAPIRequest("getPosition", new HashMap<>(), true);

			List<String> statusRow = csvTable.get(0);
			operationStatus = Status.fromString(statusRow.get(0));
			operationStatus.setMsg(statusRow.get(1));

			if (operationStatus == Status.OKAY) {
				List<String> dataRow = csvTable.get(1);
				
				if (dataRow.size() == 1) {
					position = new PositionInfo(dataRow.get(0));
				} else if (dataRow.size() == 4) {
					position = new PositionInfo(dataRow.get(0), dataRow.get(1), dataRow.get(2), dataRow.get(3));
				} else {
					operationStatus = Status.CSV_ERROR;
				}
			}
		} catch (CsvParsingException e) {
			e.printStackTrace();
			operationStatus = Status.CSV_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			operationStatus = Status.IO_ERROR;
		}

		return operationStatus;
	}

	public static Status fetchGameState() {
		Status operationStatus = Status.OKAY;

		try {
			List<List<String>> csvTable = makeAPIRequest("getGameState", new HashMap<>(), true);

			List<String> statusRow = csvTable.get(0);
			operationStatus = Status.fromString(statusRow.get(0));
			operationStatus.setMsg(statusRow.get(1));

			if (operationStatus == Status.OKAY) {
				List<String> dataRow = csvTable.get(1);
				StateOfGame stateOfGame = StateOfGame.fromString(dataRow.get(0));
				
				if (stateOfGame == StateOfGame.OPEN || stateOfGame == StateOfGame.OVER) {
					gameState = new GameState(stateOfGame);
				} else {
					if (dataRow.size() == 1) {
						gameState = new GameState(dataRow.get(0));
					} else if (dataRow.size() == 2) {
						gameState = new GameState(dataRow.get(0), dataRow.get(1));
					} else if (dataRow.size() == 3) {
						gameState = new GameState(dataRow.get(0), dataRow.get(1), dataRow.get(2));
					} else {
						operationStatus = Status.CSV_ERROR;
					}
				}
			}
		} catch (CsvParsingException e) {
			e.printStackTrace();
			operationStatus = Status.CSV_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			operationStatus = Status.IO_ERROR;
		}

		return operationStatus;
	}
	
	public static Status fetchDrXLog() {
		Status operationStatus = Status.OKAY;
		List<Ticket> tempList = new ArrayList<Ticket>();

		try {
			Map<String, String> parameters = new HashMap<>();
			parameters.put("gameID", getGameID());

			List<List<String>> csvTable = makeAPIRequest("getDrXLog", parameters);
			
			List<String> statusRow = csvTable.get(0);
			System.out.println(statusRow);
			operationStatus = Status.fromString(statusRow.get(0));
			operationStatus.setMsg(statusRow.get(1));

			if (operationStatus == Status.OKAY) {
				for (int i = 1; i < csvTable.size(); i++) {
					tempList.add(Ticket.fromString(csvTable.get(i).get(0)));
				}
			}

			drXLog = tempList;

		} catch (CsvParsingException e) {
			e.printStackTrace();
			operationStatus = Status.CSV_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			operationStatus = Status.IO_ERROR;
		}

		return operationStatus;
	}
	
	public static Status fetchPlayerDetails() {
		Status operationStatus = Status.OKAY;
		List<InGamePlayer> tempList = new ArrayList<InGamePlayer>();

		try {
			Map<String, String> parameters = new HashMap<>();
			parameters.put("gameID", getGameID());

			List<List<String>> csvTable = makeAPIRequest("getPlayerDetails", parameters);
			
			List<String> statusRow = csvTable.get(0);
			operationStatus = Status.fromString(statusRow.get(0));
			operationStatus.setMsg(statusRow.get(1));

			if (operationStatus == Status.OKAY) {
				for (int i = 1; i < csvTable.size(); i++) {
					List<String> row = csvTable.get(i);
					tempList.add(new InGamePlayer(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5)));
				}
			}

			playerDetails = tempList;

		} catch (CsvParsingException e) {
			e.printStackTrace();
			operationStatus = Status.CSV_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			operationStatus = Status.IO_ERROR;
		}

		return operationStatus;
	}

	public static String getGameID() {
		return gameID;
	}

	public static void setGameID(String newID) {
		gameID = newID;
	}
	
	public static String getSession() {
		return ";jsessionid=" + sessionID;
	}

	public static String getSessionID() {
		return sessionID;
	}

	public static void setSessionID(String newID) {
		sessionID = newID;
	}

	public static List<List<String>> makeAPIRequest(String endpoint, Map<String, String> parameters, boolean useSession) throws IOException, CsvParsingException {
		String urlParams = ParameterStringBuilder.getParamsString(parameters);
		
		if (useSession) {
			urlParams = getSession() + urlParams;
		}

		String requestURL = apiURL + endpoint + urlParams;
		HttpURLConnection apiConnection = AIHttp.makeDefaultConnection(requestURL);
		String result = AIHttp.getResponseContent(apiConnection);
		
		if (development) {
			System.out.println(result);
		}

		try {
			return CsvUtil.fromCsvTable(result);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CsvParsingException("Error while parsing CSV text");
		}
	}

	public static List<List<String>> makeAPIRequest(String endpoint, Map<String, String> parameters) throws IOException, CsvParsingException {
		return makeAPIRequest(endpoint, parameters, false);
	}

	public static List<List<String>> makeAPIRequest(String endpoint) throws IOException, CsvParsingException {
		return makeAPIRequest(endpoint, new HashMap<>());
	}

	public static GameState getGameState() {
		return gameState;
	}

	public static PositionInfo getPosition() {
		return position;
	}
}
