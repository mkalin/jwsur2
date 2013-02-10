package aphorism;

import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;

@Path("/")
public class Adages {
    // Add aphorisms to taste...
    private String[ ] aphorisms = 
       {"What can be shown cannot be said.",
	"If a lion could talk, we could not understand him.",
	"Philosophy is a battle against the bewitchment of our intelligence by means of language.",
	"Ambition is the death of thought.",
	"The limits of my language mean the limits of my world."};

    public Adages() { }

    @GET
    @Produces({MediaType.APPLICATION_XML}) // could use "application/xml" instead
    public JAXBElement<Adage> getXml() {
	return transform2Document(createAdage());
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/json")
    public String getJson() {
	return "json\n";
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/plain")
    public String getPlain() {
	return createAdage().toString() + "\n";
    }
    
    // Java object
    private Adage createAdage() {
	Adage adage = new Adage();
	adage.setWords(aphorisms[new Random().nextInt(aphorisms.length)]);
	return adage;
    }

    // Java object as XML/JSON document
    @XmlElementDecl(namespace = "http://aphorism.adage", name = "adage")
    private JAXBElement<Adage> transform2Document(Adage adage) {
	return new JAXBElement<Adage>(new QName("adage"), Adage.class, null, adage);
    }
} 