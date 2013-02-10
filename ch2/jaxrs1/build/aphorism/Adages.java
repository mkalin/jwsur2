package aphorism;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/")
public class Adages {
    private ArrayList<String> adages;

    public Adages() {
	String[ ] strings = 
	    {"What can be shown cannot be said.",
	     "If a lion could talk, we could not understand him.",
	     "Philosophy is a battle against the bewitchment of our intelligence by means of language."};
	adages = new ArrayList<String>();
	for (String string : strings) adages.add(string);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("/xml")
    public String getXML() {
	return adages.toString();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJSON() {
	return adages.toString();
    }
  
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String getPlain() {
	return adages.toString();
    }

    @GET
    @Produces({MediaType.TEXT_HTML})
    public String getHTML() {
	return adages.toString();
    }
} 