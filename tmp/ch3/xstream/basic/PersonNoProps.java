import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PersonNoProps  {
    private String name;
    private int    age;
    private String gender;

    public PersonNoProps(String name, int age, String gender){
	this.name = name;
	this.age = age;
	this.gender = gender;
    }
}

class Main {
    public static void main(String[ ] args) {
	PersonNoProps bd = new PersonNoProps("Bjoern Daehlie", 49, "Male");
	XStream xstream = new XStream(new DomDriver());
	xstream.alias("skier", PersonNoProps.class); // for readability

	// serialize
	String xml = xstream.toXML(bd);
	System.out.println(xml);
	PersonNoProps bdClone = (PersonNoProps) xstream.fromXML(xml);
	System.out.println(xstream.toXML(bdClone));
    }
}
/*
<skier>
  <name>Bjoern Daehlie</name>
  <age>49</age>
  <gender>Male</gender>
</skier>
*/