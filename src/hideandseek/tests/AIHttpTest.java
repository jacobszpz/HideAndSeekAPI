package hideandseek.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.junit.jupiter.api.Test;

import hideandseek.util.AIHttp;

class AIHttpTest {

	@Test
	void testMakeConnection() throws IOException {
		HttpURLConnection googleConnection = AIHttp.makeDefaultConnection("http://google.com");
		assertNotNull(googleConnection);	
	}
	
	@Test
	void testReadConnection() throws IOException {
		HttpURLConnection googleConnection = AIHttp.makeDefaultConnection("http://google.com");
		String response = AIHttp.getResponseContent(googleConnection);

		assertNotNull(googleConnection);
		assertNotNull(response);
		assertNotEquals("", response);
	}
	
	@Test
	void testTimeout() {
		try {
			HttpURLConnection googleConnection = AIHttp.makeDefaultConnection("http://google.com:81");
			AIHttp.getResponseContent(googleConnection);
			fail();
		} catch (IOException e) {	
			return;
		}
	}
}
