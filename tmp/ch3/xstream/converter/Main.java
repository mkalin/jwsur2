import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Main {
    public static void main(String[ ] args) {
	// Create a person and set only the name.
	PersonProps person = new PersonProps();
	person.setName("Bruno");
	
	XStream xstream = new XStream(new DomDriver());
	xstream.registerConverter(new PersonPropsConverter());
	xstream.alias("person", PersonProps.class);

	String xml = xstream.toXML(person);
	System.out.println(xml);
	PersonProps clone = (PersonProps) xstream.fromXML(xml);
	System.out.println(clone.getName()); // Bruno
    }
}

