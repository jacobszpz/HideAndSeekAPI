package hideandseek.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hideandseek.util.CsvParsingException;

class CsvParsingExceptionTest {

	@Test
	void test() {
		String errorMsg = "Error while parsing";
		try {
			throw new CsvParsingException(errorMsg);
		} catch (CsvParsingException e) {
			assertEquals(errorMsg, e.getMessage());
		}		
	}

}
