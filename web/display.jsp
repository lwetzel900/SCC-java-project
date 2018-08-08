<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Practice 2</title>
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <p>Just item.lastName causes an error because item is a LinkedHashMap$Entry not a Person.
            A LinkedHashMap$Entry has a key and a value, the stored Person is in the .value</p>
        <br>
        <h2 class="error">"${message}"</h2>

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
                    <td>${item.value.employeeID}</td>
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
        <form action="Controller" method="post">
            <input type="hidden" name="action" value="home">
            <input type="submit" value="Home">
        </form>
        <br>
        <form action="Controller" method="post">
            <input type="hidden" name="action" value="add">

            <label>Employee ID  </label>
            <input class="searchInput" type="text" name="empID" ><br>

            <label>First Name </label>
            <input class="searchInput" type="text" name="fName" ><br>

            <label>Middle Name </label>
            <input class="searchInput" type="text" name="mName" ><br>

            <label>Last Name  </label>
            <input class="searchInput" type="text" name="lName" ><br>

            <label>Birth Date</label>
            <input class="searchInput" type="date" name="bDay" max="${today}"><br>

            <label>Hire Date </label>
            <input class="searchInput" type="date" name="hireDate" value="${today}" max="${today}"><br>

            <label>&nbsp;</label>
            <input class="searchInput"  type="submit" value="Add">
        </form>

        <%-- <c:forEach var="item" items="${linkMap}" varStatus="status">
             ${status.index}
             ${item.key}
             ${item.value.lastName}
        ${item.value.birthDate}
    </c:forEach>  --%>
    </body>
</html>