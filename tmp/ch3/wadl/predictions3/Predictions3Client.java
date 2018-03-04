package predictions3;

import java.net.URI;

public class Predictions3Client {
    public static void main(String[]  args) {
	new Predictions3Client().demo();
    }

    private void demo() {
	// Get a reference to the Localhost_Predictions3ResourcesP.Root defined
	// inside Localhost_Predictions3ResourcesP, as the "root" provides access
	// to other classes, which in turn support the CRUD functionalities.
	Localhost_Predictions3ResourcesP.Root root = Localhost_Predictions3ResourcesP.root();

	// The xmlGetter can make GET requests against the service.
	Localhost_Predictions3ResourcesP.Root.Xml xmlGetter = root.xml();
	String xml = xmlGetter.getAsXml(String.class); // String is return type
	System.out.println("The raw XML:\n" + xml);    // predictions as XML doc
    }
}