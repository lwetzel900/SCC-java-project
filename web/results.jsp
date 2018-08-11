<%-- 
    Document   : results
    Created on : Aug 5, 2018, 9:18:26 PM
    Author     : lwetz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
    </head>
    <body>
        <h2>Search Results</h2><br>
        <%-- <h3 class="error">${errorMessage}</h3> --%>

        <c:if test="${message != null}">
            <h2>${message}</h2>
            
            <c:if test="${!empMap.isEmpty()}">
                <div style="overflow-x:auto;">
                    <table>
                        <tr>
                            <th>First Name</th>
                            <th>Middle Name</th> 
                            <th>Last Name</th>
                            <th>Employee ID</th>
                            <th>Birth Date</th>
                            <th>Hire Date</th>
                        </tr>

                        <c:forEach var="item" items="${empMap}">
                            <tr>
                                <td>${item.value.firstName}</td>
                                <td>${item.value.middleName}</td>
                                <td>${item.value.lastName}</td>
                                <td>${item.value.employeeID}</td>
                                <td>${item.value.birthDate}</td>
                                <td>${item.value.hireDate}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </c:if>

        <br>
        <a href="Controller">Home</a>
    </body>
</html>
