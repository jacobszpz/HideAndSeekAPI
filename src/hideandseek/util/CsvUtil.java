package hideandseek.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
	/**
	 * CsvUtil: A Java utility adapted from the source code of AppInventor.
	 * Converts CSV-like strings into Lists/ArrayLists
	 */

	public static List<List<String>> fromCsvTable(String csvString) throws Exception {
		CsvParser csvParser = new CsvParser(new StringReader(csvString));
		List<List<String>> csvList = new ArrayList<List<String>>();
		
		while (csvParser.hasNext()) {
			csvList.add(csvParser.next());
		}

		csvParser.throwAnyProblem();
		return csvList;
	}

	public static List<String> fromCsvRow(String csvString) throws Exception {
		CsvParser csvParser = new CsvParser(new StringReader(csvString));
		if (csvParser.hasNext()) {
			List<String> row = csvParser.next();
			if (csvParser.hasNext()) {
				// more than one row is an error
				throw new IllegalArgumentException("CSV text has multiple rows. Expected just one row.");
			}
			csvParser.throwAnyProblem();
			return row;
		}
		throw new IllegalArgumentException("CSV text cannot be parsed as a row.");
	}
}
