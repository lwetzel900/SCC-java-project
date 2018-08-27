<%-- 
    Document   : index
    Created on : Aug 15, 2018, 10:03:21 PM
    Author     : lwetz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Thanks for stopping by our site.</h1>
        <p>
            Welcome to So and So Inc. We are all about some random stuff that
            really does not interest you, but you are here anyways. 
        <form action="Controller" method="post">
            <input type="hidden" name="action" value="show">
            While you are here check out our <input type="submit" value="employees">.
        </form><br>
        <form action="Controller" method="post">
            <input type="hidden" name="action" value="addEmp">
            Or you can <input type="submit" value="add"> an employee to the list.
        </form><br>
        <form action="Controller" method="post">
            <input type="hidden" name="action" value="search">
            Or you can <input type="submit" value="snoop"> through our employees.
        </form>
    </p>
</body>
</html>
