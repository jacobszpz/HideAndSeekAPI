package hideandseek.objects;

public enum Status {
	OKAY, FINISHED, MISSING_PARAM, INVALID_PARAM, CANNOT_JOIN, NAME_EXISTS, INVALID_SESSION, ERROR, CSV_ERROR, IO_ERROR, NOT_POSSIBLE;

	private String msg;
	
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
		case "choose another name":
			return NAME_EXISTS;
		case "invalid player name":
			return INVALID_SESSION;
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
		case NAME_EXISTS:
			return "NAME_EXISTS";
		case INVALID_SESSION:
			return "INVALID_SESSION";
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
