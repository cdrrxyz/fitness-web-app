<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset Password Page</title>
    <link rel="stylesheet" href="resources/css/resetPassword.css" />
</head>
<body>
    <jsp:include page="home.jsp" />
    <div class="reset-pw-form">
        <form action="/resetPassword" method="post">
            <h1>Reset Password</h1>
            <p>${errorMsg}</p>
            <label for="email">Email</label>
            <input type="text" id="email" name="email" required>
            <label for="password1">New Password</label>
            <input type="password" id="password1" name="password1" required>
            <label for="password2">Confirm New Password</label>
            <input type="password" id="password2" name="password2" required>
            <button type="submit">Reset</button>
        </form>
    </div>
</body>
</html>
