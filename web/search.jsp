<%-- 
    Document   : search
    Created on : Aug 2, 2018, 10:53:51 PM
    Author     : lwetz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
    </head>
    
        <h2>Search for employees hired before or after date picked.</h2><br>
        <h3 class="error">${errorMessage}</h3>
        <form action="Controller" method="post">
            <input type="hidden" name="action" value="searchResults">

            <label>Choose a date:  </label>
            <c:if test="${hireDate != null}">
                <input class="searchInput" type="date" name="searchDate" value="${hireDate}" max="${today}"><br>
            </c:if>
            <c:if test="${hireDate == null}">
                <input class="searchInput" type="date" name="searchDate" value="${today}" max="${today}"><br>
            </c:if>

            <label>&nbsp;</label>
            <input class="searchInput" type="radio" name="searchValue" value="before">Before<br>

            <label>&nbsp;</label>
            <input class="searchInput" type="radio" name="searchValue" value="after">After<br>           

            <label>&nbsp;</label>
            <input class="searchInput" type="submit" value ="Search">
        </form><br>

         <c:if test="${message != null}">
             <h2>${message}</h2>
             <c:if test="${!linkMap.isEmpty()}">
                     <table style="overflow-x:auto;">
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
            </c:if>
        </c:if> 

        <br>
        <a href="Controller">Home</a>
    
</html>
