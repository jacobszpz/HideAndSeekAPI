package hideandseek.objects;

public enum StateOfGame {
	OPEN, FUGITIVE, DETECTIVE, OVER;

	public static StateOfGame fromString(String state) {
		switch (state) {
		case "OPEN":
			return OPEN;
		case "FUGITIVE":
			return FUGITIVE;
		case "DETECTIVE":
			return DETECTIVE;
		case "OVER":
			return OVER;
		default:
			return OPEN;
		}
	}

	public static String toString(StateOfGame state) {
		switch (state) {
		case OPEN:
			return "OPEN";
		case FUGITIVE:
			return "FUGITIVE";
		case DETECTIVE:
			return "DETECTIVE";
		case OVER:
			return "OVER";
		default:
			return "OPEN";
		}
	}
}
