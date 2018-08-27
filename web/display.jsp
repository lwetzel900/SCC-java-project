<%-- 
    Document   : display
    Created on : Aug 15, 2018, 10:03:21 PM
    Author     : lwetz
--%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>All Employees</title>
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Employees</h1>
        <p>Here is a list of all our employees. They are ordered by employee id.</p>
        <br>
        <a href="Controller">Home</a>
        <br>
        <h2 class="error">${message}</h2>

        <table style="overflow-x:auto;">
            <tr>
                <th>Employee ID</th>
                <th>First Name</th>
                <th>Middle Name</th> 
                <th>Last Name</th>
                <th>Birth Date</th>
                <th>Hire Date</th>
                <th></th>
            </tr>

            <c:forEach var="item" items="${linkMap}" varStatus="status">
                <tr>
                    <td>${item.key}</td>
                    <td>${item.value.firstName}</td>
                    <td>${item.value.middleName}</td>
                    <td>${item.value.lastName}</td>
                    <td>${item.value.birthDate}</td>
                    <td>${item.value.hireDate}</td>
                    <td><form action="Controller" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="empID" value="${item.value.employeeID}">
                            <input type="submit" value="Delete">
                        </form></td>
                </tr>
            </c:forEach>
        </table>
        <br>
    </body>
</html>