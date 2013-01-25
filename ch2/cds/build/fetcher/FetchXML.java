package fetcher;

/* Backend POJO for a JSP script. 
   The bean 
      -- gets data, in XML format, from a W3C web site
      -- converts the data to JSON format
      -- returns the JSON
*/

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FetchXML {
    // json property
    public void setJson(JSONObject json) { } // no-op

    public JSONObject getJson() {
	JSONObject json = null;
	try {
	    // Fetch the XML document from the W3C site.
	    String xml = "";
	    URL url = new URL("http://www.w3schools.com/xml/cd_catalog.xml");
	    URLConnection conn  = url.openConnection();
	    BufferedReader in = 
		new BufferedReader(new InputStreamReader(conn.getInputStream()));

	    // Read the document records.
	    String line = null;
	    while ((line = in.readLine()) != null) 
		xml += line;
	    in.close();

	    // Clean up the XML.
	    xml = xml.replace("'", "");

	    // Transform the XML document into a JSON object,
	    // in this case an array of song objects.
	    json = XML.toJSONObject(xml.toLowerCase());
	}
	catch(Exception e) { }
	return json;
    }
}
   