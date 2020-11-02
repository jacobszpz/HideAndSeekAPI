package hideandseek.objects;

public class GameState {
	private StateOfGame state;
	private int round;
	private String msg = "";
	
	public GameState(String state, String round, String msg) {
		this.state = StateOfGame.fromString(state);
		this.round = Integer.valueOf(round);
		this.msg = msg;
	}
	
	public GameState(StateOfGame state, String round, String msg) {
		this.state = state;
		this.round = Integer.valueOf(round);
		this.msg = msg;
	}
	
	public GameState(String state, String msg) {
		this.state = StateOfGame.fromString(state);
		this.msg = msg;
	}
	
	public GameState(String state) {
		this.state = StateOfGame.fromString(state);
	}

	public GameState(StateOfGame state) {
		this.state = state;
	}

	public StateOfGame getState() {
		return state;
	}
	public void setState(StateOfGame state) {
		this.state = state;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
