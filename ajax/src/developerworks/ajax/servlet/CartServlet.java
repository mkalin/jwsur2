
package developerworks.ajax.servlet;

import developerworks.ajax.store.Cart;
import javax.servlet.http.*;

import java.util.Enumeration;

public class CartServlet extends HttpServlet {

  /**
   * Updates Cart, and outputs XML representation of contents
   */
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {

    Enumeration headers = req.getHeaderNames();
    while (headers.hasMoreElements()) {
      String header  =(String) headers.nextElement();
      System.out.println(header+": "+req.getHeader(header));
    }

    Cart cart = getCartFromSession(req);

    String action = req.getParameter("action");
    String item = req.getParameter("item");
    
    if ((action != null)&&(item != null)) {

      if ("add".equals(action)) {
        cart.addItem(item);

      } else if ("remove".equals(action)) {
        cart.removeItems(item);

      }
    }

    String cartXml = cart.toXml();
    res.setContentType("text/xml");
    res.getWriter().write(cartXml);
  }

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {
    // Bounce to post, for debugging use
    // Hit this servlet directly from the browser to see XML
    doPost(req,res);
  }

  private Cart getCartFromSession(HttpServletRequest req) {

    HttpSession session = req.getSession(true);
    Cart cart = (Cart)session.getAttribute("cart");
   
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart", cart);
    }

    return cart;
  }
}
