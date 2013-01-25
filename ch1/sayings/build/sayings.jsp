<!-- Connect to the backend Predictions POJO and
     set the ServletContext. -->
<jsp:useBean id    = "preds" 
	     type  = "cliches.Predictions" 
	     class = "cliches.Predictions"> 

  <% // Check the HTTP verb: if it's anything but GET,
     // return a 405 (Method Not Allowed) status code.
     String verb = request.getMethod();

     if (!verb.equalsIgnoreCase("GET")) {
       response.sendError(response.SC_METHOD_NOT_ALLOWED,
                          "GET requests only are allowed.");
     }
     else {
       // Object reference application has the value 
       // pageContext.getServletContext()
       preds.setServletContext(application);
       out.println(preds.getPredictions());
     }
  %>
</jsp:useBean>  
