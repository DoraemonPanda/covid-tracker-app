<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.*" %>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 
    
    
    	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CoronaVirus Tracker Application</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>  
<div class="container">	 

<h1>CoronaVirus Tracker Application</h1>
<p>This application lists the current number of cases reported across the world</p>

<div class="jumbotron">
  <h1 class="display-4">${totalReportdCases}</h1>
  <p class="lead">Total cases reported in the world</p>
  <hr class="my-4">
  <p>
  <span>Total new cases reported since the previous day:</span>
  <span><b>${totalNewCases}</b></span>
  
  </p>
  

</div>

<table class="table">
  <tr>
    <th>Country</th>
    <th>State</th>
    <th>Total Cases</th>
    <th>Changes Since Last Day</th>
  </tr>
    <c:forEach items="${location}" var="elements">
        <tr>
            <td>${elements.country}</td>
            <td>${elements.state}</td>
            <td>${elements.latestTotalCases}</td>
            <td>${elements.diffFromPrevDay}</td>
        </tr>
    </c:forEach>             
</table> 
</div> 

    
 
 

 

     

   

</body>
</html>