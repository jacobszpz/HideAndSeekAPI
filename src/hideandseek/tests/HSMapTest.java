package hideandseek.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import hideandseek.objects.HSMap;

class HSMapTest {

	@Test
	void createMapTest() {
		String name = "Mini_Map";
		List<String> rounds = Arrays.asList(new String[]{"3", "8", "12", "15"});
		
		HSMap map = new HSMap(name, rounds);
		
		assertEquals(name, map.getName());
		assertEquals(name, map.toString());
		assertEquals(rounds, map.getRounds());
	}
	
	@Test
	void createEmptyMapTest() {
		String name = "Mini_Map";
		List<String> rounds = new ArrayList<>();
		
		HSMap map = new HSMap(name, rounds);
		
		assertEquals(name, map.getName());
		assertEquals(name, map.toString());
		assertEquals(rounds, map.getRounds());
	}
	
	@Test
	void createMapFromRowTest() {
		String name = "Mini_Map";

		List<String> rounds = Arrays.asList(new String[]{"3", "8", "12", "15"});
		List<String> row = Arrays.asList(new String[]{name, "3", "8", "12", "15"});
		
		HSMap map = new HSMap(row);
		
		assertEquals(name, map.getName());
		assertEquals(name, map.toString());
		assertEquals(rounds, map.getRounds());
	}
}
