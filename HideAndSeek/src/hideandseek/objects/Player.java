package hideandseek.objects;

public class Player {
	private String colour;
	private String name;

	public Player(String name, String colour) {
		this.name = name;
		this.colour = colour;
	}

	public String getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}	
}
