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
import java.io.IOException;
import java.beans.XMLEncoder;

public class PredictionsServlet extends HttpServlet {
    private Predictions predictions; // back-end bean

    // Executed when servlet is first loaded into container.
    // Create a Predictions object and set its servletContext
    // property so that the object can do I/O.
    public void init() {
	predictions = new Predictions();
	predictions.setServletContext(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String key = request.getParameter("id");
	
        // If no query string, assume client wants the full list.
        if (key == null) {
	    Map<String, Prediction> map = predictions.getMap();
	    String xml = predictions.toXML(map.values().toArray());
	    sendResponse(response, xml);
	}
	// Otherwise, return the specified Prediction.
	else {
	    Prediction pred = predictions.getMap().get(key.trim());

	    if (null == pred) { // no such Prediction
		String msg = key + " does not map to a prediction.";
		sendResponse(response, predictions.toXML(msg));
	    }
	    else {
		sendResponse(response, predictions.toXML(pred));
	    }
	}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String nums = request.getParameter("nums");
        if (nums == null)
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);

        // Extract the integers from a string such as: "[1, 2, 3]"
	/*
        nums = nums.replace('[', '\0');
        nums = nums.replace(']', '\0');
        String[ ] parts = nums.split(", ");
        List&lt;Integer&gt; list = new ArrayList&lt;Integer&gt;();
        for (String next : parts) {
            int n = Integer.parseInt(next.trim());
            cache.put(n, countRabbits(n));
            list.add(cache.get(n));
        }
        send_typed_response(request, response, list + " added.");
	*/
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String key = request.getParameter("num");
        // Only one Fibonacci number may be deleted at a time.
        if (key == null)
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
        try {
            int n = Integer.parseInt(key.trim());
	    /*
            cache.remove(n);
            send_typed_response(request, response, n + " deleted.");
	    */
        }
        catch(NumberFormatException e) {
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    public void doInfo(HttpServletRequest req, HttpServletResponse res) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    public void doHead(HttpServletRequest req, HttpServletResponse res) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    public void doOptions(HttpServletRequest req, HttpServletResponse res) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    private void sendResponse(HttpServletResponse res, String payload) {
	try {
	    OutputStream out = res.getOutputStream();
	    out.write(payload.getBytes());
	    out.flush();
	}
	catch(IOException e) {
	    throw new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}
    }
}     