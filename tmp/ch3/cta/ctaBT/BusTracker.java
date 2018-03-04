package ctaBT;

import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BusTracker {
    private static final String baseUrl = 
	"http://www.ctabustracker.com/bustime/api/v1/"; 

    public static void main(String[ ] args) {
	if (args.length < 1) {
	    System.err.println("BusTracker <CTA key>");
	    return;
	}
	new BusTracker().demo("?key=" + args[0]);
    }

    private void demo(String key) {
	// Current official CTA time.
	String url = baseUrl + "gettime" + key;
	makeRequest(url);

	url = baseUrl + "getvehicles" + key;
	makeRequest(url);
	
	String sampleRoute = "&rt=20&pid=954";
	url = baseUrl + "getpatterns" + key + sampleRoute;
	makeRequest(url);
    }

    private void makeRequest(String stringUrl) {
	String response = null;
	try {
	    URL url = new URL(stringUrl);
	    URLConnection conn = url.openConnection();
	    conn.setDoInput(true);
	    
	    BufferedReader in = 
		new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String chunk = null;
	    while ((chunk = in.readLine()) != null) response += chunk;
	    in.close();
	}
	catch(Exception e) { throw new RuntimeException("Arrrg! " + e); }
	report(stringUrl, response);
    }

    private void report(String url, String xml) {
	String msg = url + "\n" + xml;
	System.out.println(msg);
    }
}
