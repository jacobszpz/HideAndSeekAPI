package hideandseek.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hideandseek.objects.Status;

class StatusTest {

	@Test
	void fromStringTest() {
		assertEquals(Status.OKAY, Status.fromString("OK"));
		assertEquals(Status.MISSING_PARAM, Status.fromString("Missing required parameter"));
		assertEquals(Status.INVALID_PARAM, Status.fromString("Invalid parameter"));
		assertEquals(Status.CANNOT_JOIN, Status.fromString("Cannot join game"));
		assertEquals(Status.NAME_EXISTS, Status.fromString("Choose another name"));
		assertEquals(Status.INVALID_SESSION, Status.fromString("Invalid player name"));
		assertEquals(Status.ERROR, Status.fromString("Error"));
		assertEquals(Status.ERROR, Status.fromString("Made up error"));
	}

	@Test
	void toStringTest() {
		assertEquals("OKAY", Status.toString(Status.OKAY));
		assertEquals("MISSING_PARAM", Status.toString(Status.MISSING_PARAM));
		assertEquals("INVALID_PARAM", Status.toString(Status.INVALID_PARAM));
		assertEquals("CANNOT_JOIN", Status.toString(Status.CANNOT_JOIN));
		assertEquals("NAME_EXISTS", Status.toString(Status.NAME_EXISTS));
		assertEquals("INVALID_SESSION", Status.toString(Status.INVALID_SESSION));
		assertEquals("ERROR", Status.toString(Status.ERROR));
		assertEquals("CSV_ERROR", Status.toString(Status.CSV_ERROR));
		assertEquals("IO_ERROR", Status.toString(Status.IO_ERROR));
		assertEquals("NOT_POSSIBLE", Status.toString(Status.NOT_POSSIBLE));
	}
	
	@Test
	void setMsgTest() {
		String success = "Operation successful";
		Status.OKAY.setMsg(success);
		assertEquals(success, Status.OKAY.getMsg());
		assertNotNull(Status.ERROR.getMsg());
	}
	
	@Test
	void nullMsgTest() {
		assertNotNull(Status.IO_ERROR.getMsg());
		
	}
}
