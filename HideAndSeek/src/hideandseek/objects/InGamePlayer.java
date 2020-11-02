package hideandseek.objects;

public class InGamePlayer extends Player {
	private String location;
	private int yellow;
	private int green;
	private int red;

	public InGamePlayer(String name, String colour, String location, int y, int g, int r) {
		super(name, colour);
		this.location = location;
		this.yellow = y;
		this.green = g;
		this.red = r;
	}

	public InGamePlayer(String name, String colour, String location, String y, String g, String r) {
		this(name, colour, location, Integer.valueOf(y), Integer.valueOf(g), Integer.valueOf(r));
	}

	public String getLocation() {
		return location;
	}
	
	public int getYellow() {
		return yellow;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getRed() {
		return red;
	}
}
