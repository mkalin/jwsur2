package cliches2;

import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
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
	    Map<String, Prediction> map = predictions.getMap();
	    String xml = predictions.toXML(map.values().toArray());
	    sendResponse(response, xml, json);
	}
	// Otherwise, return the specified Prediction.
	else {
	    Prediction pred = predictions.getMap().get(key);

	    if (pred == null) { // no such Prediction
		String msg = key + " does not map to a prediction.";
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
	String id = predictions.addPrediction(p);

	// Generate the confirmation message.
	String msg = "Prediction " + id + " created.";
	sendResponse(response, msg, false);
    }

    // PUT /cliches
    // HTTP body should contain at least two keys: the id (which prediction is
    // to be edited) must be present; the predictor or the prediction or both
    // should be present.
    public void doPut(HttpServletRequest req, HttpServletResponse res) {
	String key = req.getParameter("id");
	if (key == null)
	    throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);

	Prediction p = predictions.getMap().get(key);
	if (p == null) {
	    String msg = key + " does not map to a Prediction.";
	    sendResponse(res, msg, false);
	}
	else {
	    // At least one of these must be present.
	    String who = req.getParameter("who");
	    String what = req.getParameter("what");

	    if (who == null && what == null) {
		throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
	    }
	    // Do the editing.
	    else {
		if (who != null) p.setWho(who);
		if (what != null) p.setWhat(what);
		
		String msg = "Prediction " + key + " has been edited.";
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

        }
        catch(NumberFormatException e) {
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
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