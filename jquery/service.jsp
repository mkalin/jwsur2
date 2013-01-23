<%@ page import = "org.json.simple.JSONObject" %>
<%! 
   // instance field: one for the entire JSP script
   private int counter = 0;
%>

<%
   /* Extract the query string and, depending on its value,
      perform one of the following operations:
   
      null  ==> return the current counter
      inc   ==> return the incremented counter
      dec   ==> return the decremented counter
      reset ==> return the counter set to zero
   */
   String qs = request.getQueryString();
   JSONObject json = new JSONObject();

   if (null == qs)
     json.put("counter", counter);
   else if (qs.equals("inc")) 
     json.put("counter", ++counter);
   else if (qs.equals("dec"))
     json.put("counter", --counter);
   else if (qs.equals("reset")) {
     counter = 0;
     json.put("counter", counter);
   }
   else // same as null case
     json.put("counter", counter);

   // Pause for 5 seconds for demo purposes.
   try {
      Thread.sleep(5);
   } catch(Exception e) { }

   // Send the JSON to requester.
   out.print(json.toJSONString());
   out.flush();
%>



