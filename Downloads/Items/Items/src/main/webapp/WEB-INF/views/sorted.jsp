<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sorted Students</title>
    <link rel="stylesheet" href="<c:url value="/styles.css" />">
</head>
<body>
    <h1>Sorted Students</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <!-- Add more columns as per your Student entity -->
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${sortedStudents}" var="student">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.email}</td>
                    <!-- Add more columns as per your Student entity -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
