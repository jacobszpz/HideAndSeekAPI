package hideandseek.objects;

import java.util.ArrayList;
import java.util.List;

public class HSMap {
	private String name;
	private List<String> rounds = new ArrayList<String>();

	public HSMap(String name, List<String> rounds) {
		this.name = name;
		this.rounds = rounds;
	}

	public HSMap(List<String> csv_row) {
		this.name = csv_row.get(0);

		for (int i = 1; i < csv_row.size(); i++) {
			this.rounds.add(csv_row.get(i));
		}
	}

	public String getName() {
		return name;
	}

	public List<String> getRounds() {
		return rounds;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
