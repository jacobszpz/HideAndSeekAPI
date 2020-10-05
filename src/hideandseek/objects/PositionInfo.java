package hideandseek.objects;

public class PositionInfo {
	private String location;
	private int yellow;
	private int green;
	private int red;
	
	public PositionInfo(String location, String y, String g, String r) {
		this.location = location;
		this.yellow = Integer.valueOf(y);
		this.green = Integer.valueOf(g);
		this.red = Integer.valueOf(r);
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
