// JSP File (jobPost.jsp)
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Post a Job</title>
</head>
<body>
    <h1>Post a Job</h1>
    <form action="JobServlet" method="post">
        <label for="jobTitle">Job Title:</label>
        <input type="text" id="jobTitle" name="jobTitle" required><br><br>
        <label for="jobDescription">Job Description:</label><br>
        <textarea id="jobDescription" name="jobDescription" rows="5" cols="40" required></textarea><br><br>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
