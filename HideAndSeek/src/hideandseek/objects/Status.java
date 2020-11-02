package hideandseek.objects;

public enum Status {
	OKAY, FINISHED, MISSING_PARAM, INVALID_PARAM, CANNOT_JOIN, CANNOT_START, 
	NAME_EXISTS, BAD_MOVE, NOT_IN_PLAY, INVALID_SESSION,
	DB_ERROR, ERROR, CSV_ERROR, IO_ERROR, NOT_POSSIBLE;

	private String msg = "";
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public static Status fromString(String status) {
		switch (status.toLowerCase()) {
		case "ok":
			return OKAY;
		case "finished":
			return FINISHED;
		case "missing required parameter":
			return MISSING_PARAM;
		case "invalid parameter":
			return INVALID_PARAM;
		case "cannot join game":
			return CANNOT_JOIN;
		case "cannot start game":
			return CANNOT_START;
		case "choose another name":
			return NAME_EXISTS;
		case "bad move":
			return BAD_MOVE;
		case "not in play":
			return NOT_IN_PLAY;
		case "invalid player name":
			return INVALID_SESSION;
		case "database error":
			return DB_ERROR;
		case "error":
			return ERROR;
		default:
			return ERROR;
		}
	}

	public static String toString(Status status) {
		switch (status) {
		case OKAY:
			return "OKAY";
		case FINISHED:
			return "FINISHED";
		case MISSING_PARAM:
			return "MISSING_PARAM";
		case INVALID_PARAM:
			return "INVALID_PARAM";
		case CANNOT_JOIN:
			return "CANNOT_JOIN";
		case CANNOT_START:
			return "CANNOT_START";
		case NAME_EXISTS:
			return "NAME_EXISTS";
		case BAD_MOVE:
			return "BAD_MOVE";
		case NOT_IN_PLAY:
			return "NOT_IN_PLAY";
		case INVALID_SESSION:
			return "INVALID_SESSION";
		case DB_ERROR:
			return "DB_ERROR";
		case ERROR:
			return "ERROR";
		case CSV_ERROR:
			return "CSV_ERROR";
		case IO_ERROR:
			return "IO_ERROR";
		case NOT_POSSIBLE:
			return "NOT_POSSIBLE";
		default:
			return "ERROR";
		}
	}
}
