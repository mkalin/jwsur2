package developerworks.ajax.store;

import java.math.BigDecimal;
import java.util.*;

/**
 * A very simple shopping Cart
 */
public class Cart {

  private HashMap<Item,Integer> contents;

  /**
   * Creates a new Cart instance
   */
  public Cart() {
    contents = new HashMap<Item,Integer>();
  }

  /**
   * Adds a named item to the cart
   * @param itemName The name of the item to add to the cart
   */
  public void addItem(String itemCode) {

    Catalog catalog = new Catalog();

    if (catalog.containsItem(itemCode)) {
      Item item = catalog.getItem(itemCode);

      int newQuantity = 1;
      if (contents.containsKey(item)) {
        Integer currentQuantity = contents.get(item);
        newQuantity += currentQuantity.intValue();
      }

      contents.put(item, new Integer(newQuantity));
    }
  }

  /**
   * Removes the named item from the cart
   * @param itemName Name of item to remove
   */
  public void removeItems(String itemCode) {

    contents.remove(new Catalog().getItem(itemCode));
  }

  /**
   * @return XML representation of cart contents
   */
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

  private String getCartTotal() {
    int total = 0;

    for (Iterator<Item> I = contents.keySet().iterator() ; I.hasNext() ; ) {
      Item item = I.next();
      int itemQuantity = contents.get(item).intValue();

      total += (item.getPrice() * itemQuantity);
    }

    return "$"+new BigDecimal(total).movePointLeft(2);
  }
}
