<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sorting Algorithms</title>
    <link rel="stylesheet" href="<spring:url value="/styles.css" />">
</head>
<body>
    <h1>Sorting Algorithms</h1>
    <form action="<spring:url value="/sort" />" method="get">
        <label for="sortAlgorithm">Select Sorting Algorithm:</label>
        <select name="sortAlgorithm" id="sortAlgorithm">
            <option value="heap">Heap Sort</option>
            <option value="quick">Quick Sort</option>
            <option value="merge">Merge Sort</option>
            <option value="radix">Radix Sort</option>
            <option value="bucket">Bucket Sort</option>
        </select>
        <input type="submit" value="Sort">
    </form>
</body>
</html>
