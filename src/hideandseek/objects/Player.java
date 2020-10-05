package hideandseek.objects;

public class Player {
	public String colour;
	public String name;

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
