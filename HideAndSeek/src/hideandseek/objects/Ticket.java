package hideandseek.objects;

public enum Ticket {
	YELLOW, GREEN, RED;
	
	public static Ticket fromString(String colour) {
		switch (colour) {
			case "yellow":
				return YELLOW;
			case "green":
				return GREEN;
			case "red":
				return RED;
			default:
				return null;
		}
	}

	public static String toString(Ticket colour) {
		switch (colour) {
			case YELLOW:
				return "yellow";
			case GREEN:
				return "green";
			case RED:
				return "red";
			default:
				return null;
		}
	}
}
