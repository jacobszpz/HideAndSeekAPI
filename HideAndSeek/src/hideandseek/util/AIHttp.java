package hideandseek.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class AIHttp {
	private static int timeout = 5000;

	public static HttpURLConnection makeDefaultConnection(String requestURL) throws IOException {
		HttpURLConnection apiConnection = (HttpURLConnection) new URL(requestURL).openConnection();
		apiConnection.setRequestMethod("GET");
		apiConnection.setConnectTimeout(timeout);
		apiConnection.setReadTimeout(timeout);

		return apiConnection;
	}

	public static String getResponseContent(HttpURLConnection connection) throws IOException {
		// Use the content encoding to convert bytes to characters.
		String encoding = connection.getContentEncoding();
		if (encoding == null) {
			encoding = "UTF-8";
		}
		
		InputStreamReader reader;

		try {
			reader = new InputStreamReader(getConnectionStream(connection), encoding);
		} catch (NullPointerException e) {
			throw new IOException();
		}

		try {
			int contentLength = connection.getContentLength();
			StringBuilder sb = (contentLength != -1)
					? new StringBuilder(contentLength)
							: new StringBuilder();
					char[] buf = new char[1024];
					int read;
					while ((read = reader.read(buf)) != -1) {
						sb.append(buf, 0, read);
					}
					return sb.toString();
		} finally {
			reader.close();
		}
	}

	private static InputStream getConnectionStream(HttpURLConnection connection) throws SocketTimeoutException {
		// According to the Android reference documentation for HttpURLConnection: If the HTTP response
		// indicates that an error occurred, getInputStream() will throw an IOException. Use
		// getErrorStream() to read the error response.
		try {
			return connection.getInputStream();
		} catch (SocketTimeoutException e) {
			throw e; //Rethrow exception - should not attempt to read stream for timeouts
		} catch (IOException e1) {
			// Use the error response for all other IO Exceptions.
			return connection.getErrorStream();
		}
	}
}
