package cds;

//import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FetchXML {
    // public void setJson(JSONObject json) { } // no-op
    public void setJson(String json) { } 
    // public JSONObject getJson() {
    public String getJson() {
	JSONObject json = null;
	try {
	    // Fetch the XML document from the W3C site.
	    String xml = "";
	    URL url = new URL("https://www.w3schools.com/xml/cd_catalog.xml");
	    URLConnection conn  = url.openConnection();
	    BufferedReader in = 
		new BufferedReader(new InputStreamReader(conn.getInputStream()));

	    // Read the document records.
	    String line = null;
	    while ((line = in.readLine()) != null) xml += line;
	    in.close();

	    // Clean up the XML.
	    xml = xml.replace("'", "");

	    // Transform the XML document into a JSON object,
	    // in this case an array of song objects.
	    json = XML.toJSONObject(xml.toLowerCase());
	}
	catch(Exception e) { }
	String jsonStr = json.toString();
	int start = jsonStr.indexOf('[');
	int end = jsonStr.indexOf(']') + 1;
	if (start > 0 && end < jsonStr.length())
	    jsonStr = jsonStr.substring(start, end);

	return jsonStr; // JSON document
    }
}
   
