package cliches2;

import java.util.concurrent.ConcurrentMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.util.Arrays;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.beans.XMLEncoder;
import org.json.JSONObject;
import org.json.XML;

public class PredictionsServlet extends HttpServlet {
    private Predictions predictions; // back-end bean

    // Executed when servlet is first loaded into container.
    // Create a Predictions object and set its servletContext
    // property so that the object can do I/O.
    public void init() {
	predictions = new Predictions();
	predictions.setServletContext(this.getServletContext());
    }

    // GET /cliches2
    // GET /cliches2?id=1
    // If the HTTP Accept header is set to application/json (or an equivalent
    // such as text/x-json), the response is JSON and XML otherwise.
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String key = request.getParameter("id");

	// Check user preference for XML or JSON by inspecting
	// the HTTP headers for the Accept key.
	String accept = request.getHeader("accept");
	boolean json = accept.contains("json") ? true : false;
	
        // If no query string, assume client wants the full list.
        if (key == null) {
	    ConcurrentMap<String, Prediction> map = predictions.getMap();

	    // Sort the map's values for readability.
	    Object[] list = map.values().toArray();
	    Arrays.sort(list);

	    String xml = predictions.toXML(list);
	    sendResponse(response, xml, json);
	}
	// Otherwise, return the specified Prediction.
	else {
	    Prediction pred = predictions.getMap().get(key);

	    if (pred == null) { // no such Prediction
		String msg = key + " does not map to a prediction.\n";
		sendResponse(response, predictions.toXML(msg), false);
	    }
	    else { // requested Prediction found
		sendResponse(response, predictions.toXML(pred), json);
	    }
	}
    }

    // POST /cliches2
    // HTTP body should contain two keys, one for the predictor ("who") and
    // another for the prediction ("what").
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
	String who = request.getParameter("who");
	String what = request.getParameter("what");

	// Are the data to create a new prediction present?
        if (who == null || what == null)
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);

	// Create a Prediction.
	Prediction p = new Prediction();
	p.setWho(who);
	p.setWhat(what);

	// Save the ID of the newly created Prediction.
	int id = predictions.addPrediction(p);

	// Generate the confirmation message.
	String msg = "Prediction " + id + " created.\n";
	sendResponse(response, msg, false);
    }

    // PUT /cliches
    // HTTP body should contain at least two keys: the id (which prediction is
    // to be edited) must be present; the predictor or the prediction or both
    // should be present. See documentation below, however.
    public void doPut(HttpServletRequest req, HttpServletResponse res) {
	/* A workaround is necessary for a PUT request to Tomcat, which does 
	   not parse the request stream to generate the parameter map. A
	   hack is thus required. */
	String key = null;
	String rest = null;
	boolean who = false;

	/* Let the hack begin. */
	try {
	    BufferedReader br = 
		new BufferedReader(new InputStreamReader(req.getInputStream()));
	    String data = br.readLine();

	    System.err.println("########### " + data);

	    /* To simplify the hack, assume that the PUT request has exactly
	       two parameters: the id and either who or what. Assume, further,
	       that the id comes first. From the client side, a hash character
	       # separates the id and the who/what, e.g.,

	          id=33#who=Homer Allision
	     */
	    String[] args = data.split("#");      // id in args[0], rest in args[1]
	    String[] parts1 = args[0].split("="); // id = parts1[1]
	    key = parts1[1];

	    String[] parts2 = args[1].split("="); // parts2[0] is key 
	    if (parts2[0].contains("who")) who = true;
	    rest = parts2[1];
	}
	catch(Exception e) { 
	    throw new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	if (key == null)
	    throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);

	Prediction p = predictions.getMap().get(key);
	if (p == null) {
	    String msg = key + " does not map to a Prediction.\n";
	    sendResponse(res, msg, false);
	}
	else {
	    if (rest == null) {
		throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
	    }
	    // Do the editing.
	    else {
		if (who) p.setWho(rest);
		else p.setWhat(rest);

		String msg = "Prediction " + key + " has been edited.\n";
		sendResponse(res, msg, false);
	    }
	}
    }

    // DELETE /cliches2?id=1
    public void doDelete(HttpServletRequest req, HttpServletResponse res) {
        String key = req.getParameter("id");
        // Only one Prediction can be deleted at a time.
        if (key == null)
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
        try {
	    predictions.getMap().remove(key);
        }
        catch(Exception e) {
            throw new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // Method Not Allowed
    public void doInfo(HttpServletRequest req, HttpServletResponse res) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    // Method Not Allowed
    public void doHead(HttpServletRequest req, HttpServletResponse res) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    // Method Not Allowed
    public void doOptions(HttpServletRequest req, HttpServletResponse res) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    // Send the response payload to the client.
    private void sendResponse(HttpServletResponse res, String payload, boolean json) {
	try {
	    // Convert to JSON?
	    if (json) {
		JSONObject jobt = XML.toJSONObject(payload);
		payload = jobt.toString(3); // 3 is indentation level for nice look
	    }

	    OutputStream out = res.getOutputStream();
	    out.write(payload.getBytes());
	    out.flush();
	}
	catch(Exception e) {
	    throw new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}
    }
}     