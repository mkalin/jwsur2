<!-- Connect to the backend Predictions POJO and
     set the ServletContext. -->
<jsp:useBean id    = "preds" 
	     type  = "cliches.Predictions" 
	     class = "cliches.Predictions"> 
  <%
     // Object reference application has the value 
     // pageContext.getServletContext()
     preds.setServletContext(application);
  %>
</jsp:useBean>  

<!-- Return the XML representation of the predictions. -->
<jsp:getProperty name =     "preds"
		 property = "predictions"/>
