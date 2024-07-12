<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top Bar</title>
    <link rel="stylesheet" type="text/css" href="resources/css/topbar.css">
</head>
<body>
    <%
        String loggedIn = (String) request.getAttribute("loggedIn");
        if (loggedIn == "true") { %>
    <div class="top-bar">
        <a href="discussion">DISCUSSION</a>
        <a href="guide">GUIDE</a>
        <a href="group">GROUP</a>
        <a href="profile">PROFILE</a>
        <a href="logout">LOGOUT</a>
    </div>
    <% } else { %>
    <div class="top-bar">
        <a href="discussion">DISCUSSION</a>
        <a href="guide">GUIDE</a>
        <a href="login">LOGIN</a>
    </div>
    <% } %>
</body>
</html>
