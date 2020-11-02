package hideandseek.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import hideandseek.util.ParameterStringBuilder;

class ParameterStringBuilderTest {

	@Test
	void testEmptyMap() {
		Map<String, String> parameters = new HashMap<String, String>();

		try {
			String urlParams = ParameterStringBuilder.getParamsString(parameters);
			assertEquals("", urlParams);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	void testRegularMap() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("user", "admin");
		parameters.put("pass", "password");

		try {
			String urlParams = ParameterStringBuilder.getParamsString(parameters);
			assertEquals("?pass=password&user=admin", urlParams);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	void testNumericMap() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("0", "admin");
		parameters.put("1", "password");

		try {
			String urlParams = ParameterStringBuilder.getParamsString(parameters);
			assertEquals("?0=admin&1=password", urlParams);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail(e);
		}	
	}

	@Test
	void testURLEncoding() {
		Map<String, String> parameters = new HashMap<String, String>();
		String specialChars = "!#$%&/()";
		parameters.put("pass", specialChars);
		parameters.put("user", "admin");

		try {
			String urlParams = ParameterStringBuilder.getParamsString(parameters);
			String decodedURL = URLDecoder.decode(urlParams, "UTF-8");
			assertEquals(String.format("?pass=%s&user=admin", specialChars), decodedURL);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail(e);
		}	
	}
}
