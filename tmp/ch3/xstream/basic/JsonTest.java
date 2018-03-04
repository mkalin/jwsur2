import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class JsonTest {
    public static void main(String[ ] args) {
	PersonNoProps bd = new PersonNoProps("Bjoern Daehlie", 49, "Male");

	XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
	String json = xstream.toXML(bd); // it's really toJson now
	System.out.println(json);
    }
}