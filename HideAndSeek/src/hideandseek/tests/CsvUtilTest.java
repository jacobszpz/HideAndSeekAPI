package hideandseek.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import hideandseek.util.CsvUtil;

class CsvUtilTest {

	@Test
	void fromTableTest() {
		String csvRow = "\"OK\",\"Okay\"\n"
				+ "\"Mini_Map\",\"3\",\"8\"\n"
				+ "\"Lesley\",\"5\",\"10\"";

		List<String> statusRow = Arrays.asList("OK", "Okay");
		List<String> mapRowOne = Arrays.asList("Mini_Map", "3", "8");
		List<String> mapRowTwo = Arrays.asList("Lesley", "5", "10");

		List<List<String>> csvTable = Arrays.asList(statusRow, mapRowOne, mapRowTwo);
		
		try {
			assertEquals(csvTable, CsvUtil.fromCsvTable(csvRow));
		} catch (Exception e) {
			e.printStackTrace();
			fail("CSV exception");
		}
	}
	
	@Test
	void fromRowTestWithTable() {
		String csvRow = "\"OK\",\"Okay\"\n"
				+ "\"Mini_Map\",\"3\",\"8\"\n"
				+ "\"Lesley\",\"5\",\"10\"";
		
		try {
			CsvUtil.fromCsvRow(csvRow);
		} catch (IllegalArgumentException e) {
			return;
		} catch (Exception e) {
			e.printStackTrace();
			fail("CSV exception");
		}
	}

	@Test
	void fromRowTest() {
		String csvRow = "\"OK\",\"Okay\"\n";
		List<String> statusRow = Arrays.asList("OK", "Okay");

		try {
			assertEquals(statusRow, CsvUtil.fromCsvRow(csvRow));
		} catch (Exception e) {
			e.printStackTrace();
			fail("CSV exception");
		}
	}
	
	@Test
	void fromEmptyRowTest() {
		String csvRow = "";

		try {
			CsvUtil.fromCsvRow(csvRow);
		} catch (IllegalArgumentException e) {
			return;
		} catch (Exception e) {
			e.printStackTrace();
			fail("CSV exception");
		}
	}
}
