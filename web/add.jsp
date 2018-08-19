<%-- 
    Document   : add
    Created on : Aug 15, 2018, 10:03:21 PM
    Author     : lwetz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Add an Employee</h1>
        
        <a href="Controller">Home</a>
        <br>
        <h2 class="error">${message}</h2>
        <br>
        <form action="Controller" method="post">
            <input type="hidden" name="action" value="add">

            <label>Employee ID  </label>
            <input class="searchInput" type="text" name="empID" value="${empID}"><br>

            <label>First Name </label>
            <input class="searchInput" type="text" name="fName" value="${fName}"><br>

            <label>Middle Name </label>
            <input class="searchInput" type="text" name="mName" value="${mName}"><br>

            <label>Last Name  </label>
            <input class="searchInput" type="text" name="lName" value="${lName}"><br>

            <label>Birth Date</label>
            <input class="searchInput" type="date" name="bDay" max="${today}"value="${bDay}"><br>

            <label>Hire Date </label>
            <input class="searchInput" type="date" name="hireDate" value="${hireDate}" max="${today}"><br>

            <label>&nbsp;</label>
            <input class="searchInput"  type="submit" value="Add">
            
        </form>
    </body>
</html>
