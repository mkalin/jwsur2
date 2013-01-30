package cliches2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.beans.XMLEncoder; // simple and effective
import javax.servlet.ServletContext;

public class Predictions {
    private Map<String, Prediction> predictions;
    private ServletContext sctx;
    private static int mapKey = 1;

    public Predictions() { 
	predictions = 
	    Collections.synchronizedMap(new HashMap<String, Prediction>());
    }

    //** properties

    // The ServletContext is required to read the data from
    // a text file packaged inside the WAR file
    public void setServletContext(ServletContext sctx) {
	this.sctx = sctx;
    }
    public ServletContext getServletContext() { return this.sctx; }

    public void setMap(Map<String, Prediction> predictions) { 
	// no-op for now
    } 
    public Map<String, Prediction> getMap() {
	// Has the ServletContext been set?
	if (null == getServletContext()) return null;      

	// Have the data been read already?
	if (null == predictions) populate(); 

	return this.predictions;
    }

    public String toXML() {
	String xml = null;
	try {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    XMLEncoder encoder = new XMLEncoder(out);
	    encoder.writeObject(predictions.values().toArray()); // serialize to XML
	    encoder.close();
	    xml = out.toString(); // stringify
	}
	catch(Exception e) { }
	return xml;
    }

    //** utility
    private void populate() {
	String filename = "/WEB-INF/data/predictions.db";
	InputStream in = sctx.getResourceAsStream(filename);

	// Read the data into the array of Predictions. 
	if (in != null) {
	    try {
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader reader = new BufferedReader(isr);

		int i = 0;
		String record = null;
		while ((record = reader.readLine()) != null) {
		    String[] parts = record.split("!");
		    Prediction p = new Prediction();
		    p.setWho(parts[0]);
		    p.setWhat(parts[1]);

		    predictions.put(String.valueOf(mapKey++), p);
		}
	    }
	    catch (IOException e) { }
	}
    }
}




