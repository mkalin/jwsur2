import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.ArrayList;

class Marshal {
    private static final String fileName = "bd.mar";

    public static void main(String[ ] args) {
	new Marshal().runExample();
    }

    private void runExample() {
        try {
	    JAXBContext ctx = JAXBContext.newInstance(Skier.class);
	    Marshaller m = ctx.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    
	    // Marshal a Skier object: 1st to stdout, 2nd to file
	    Skier skier = createSkier();              
	    m.marshal(skier, System.out);

	    FileOutputStream out = new FileOutputStream(fileName);
	    m.marshal(skier, out);
	    out.close();

	    // Unmarshal as proof of concept
	    Unmarshaller u = ctx.createUnmarshaller();
	    Skier bdClone = (Skier) u.unmarshal(new File(fileName));
	    System.out.println();
	    m.marshal(bdClone, System.out);
	} 
	catch(JAXBException e) {
	    System.err.println(e);
        }
	catch(IOException e) {
	    System.err.println(e);
	}
    }
    private Skier createSkier() {
	Person bd = new Person("Bjoern Daehlie", 49, "Male");
        List<String> list = new ArrayList<String>();
	list.add("12 Olympic Medals");
        list.add("9 World Championships");                
        list.add("Winningest Winter Olympian");                       
	list.add("Greatest Nordic Skier");
        return new Skier(bd, "Norway", list);
    }
}
/*
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<skier>
    <majorAchievements 
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xmlns:xs="http://www.w3.org/2001/XMLSchema" 
        xsi:type="xs:string">
      12 Olympic Medals
    </majorAchievements>
    <majorAchievements 
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xmlns:xs="http://www.w3.org/2001/XMLSchema" 
        xsi:type="xs:string">
      9 World Championships
    </majorAchievements>
    <majorAchievements 
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xmlns:xs="http://www.w3.org/2001/XMLSchema" 
        xsi:type="xs:string">
      Winningest Winter Olympian
    </majorAchievements>
    <majorAchievements 
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xmlns:xs="http://www.w3.org/2001/XMLSchema" 
        xsi:type="xs:string">
      Greatest Nordic Skier
    </majorAchievements>
    <nationalTeam>Norway</nationalTeam>
    <person>
        <age>41</age>
        <gender>Male</gender>
        <name>Bjoern Daehlie</name>
    </person>
</skier>
*/
