package scart;

import java.math.BigDecimal;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.util.HashMap;

public class Cart {
    private HashMap<Item, Integer> contents;
    
    public Cart() {
	contents = new HashMap<Item, Integer>();
    }

    public void add(String code) {
	Catalog catalog = new Catalog();

	if (catalog.containsItem(code)) {
	    Item item = catalog.getItem(code);
	    int howMany = 1;

	    if (contents.containsKey(item)) {
		Integer i = contents.get(item);
		howMany += i.intValue();
	    }   
	    contents.put(item, new Integer(howMany));
	}
    }

    public void clear(String code) {
	contents.remove(new Catalog().getItem(code));
    }

    public String toXml() {
	StringBuffer xml = new StringBuffer();
	xml.append("<?xml version=\"1.0\"?>\n");
	xml.append("<cart generated=\""+System.currentTimeMillis()+"\" total=\""+getCartTotal()+"\">\n");
	
	for (Iterator<Item> I = contents.keySet().iterator() ; I.hasNext() ; ) {
	    Item item = I.next();
	    int itemQuantity = contents.get(item).intValue();
	    
	    xml.append("<item code=\""+item.getCode()+"\">\n");
	    xml.append("<name>");
	    xml.append(item.getName());
	    xml.append("</name>\n");
	    xml.append("<quantity>");
	    xml.append(itemQuantity);
	    xml.append("</quantity>\n");
	    xml.append("</item>\n");
	}
	
	xml.append("</cart>\n");
	return xml.toString();
    }
    
    private String getTotal() {
	int total = 0;
	
	for (Iterator<Item> I = contents.keySet().iterator() ; I.hasNext() ; ) {
	    Item item = I.next();
	    int itemQuantity = contents.get(item).intValue();
	    
	    total += (item.getPrice() * itemQuantity);
	}
	
	return "$"+new BigDecimal(total).movePointLeft(2);
    }
}
