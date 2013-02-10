package aphorism;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;

public class Main {
    public static void main(String[] args) {
	String baseURL = "http://localhost:9876";
	ResourceConfig config = new PackagesResourceConfig("aphorism");
	System.out.println("Starting server on port 9876. Hit an key to stop server.\n");

	try {
	    HttpServer httpServer = GrizzlyServerFactory.createHttpServer(baseURL, config);

	    System.in.read();
	    httpServer.stop();
	}
	catch(Exception e) { }
    }    
}

