package ch.vorburger.gaexmpp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.common.io.ByteStreams;

@SuppressWarnings("serial")
public class XMPPErrorServlet extends HttpServlet {

	private static final Logger log = null; // TODO how-to use JUL? Use slf4j bridge?

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// Parse the POST data, which is sent as a MIME stream containing the stanza.
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ServletInputStream inputStream = req.getInputStream();
		ByteStreams.copy(inputStream, baos);

		// Log the error
		System.err.println(baos.toString()); // TODO remove!! ;)
		log.warning("Error stanza received: " + baos.toString());
	}
	
}