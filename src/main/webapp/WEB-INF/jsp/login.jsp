<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="/resources/css/login.css" />
</head>
<body>
    <jsp:include page="home.jsp" />
    <div class="login-form">
        <form action="login" method="post">
            <h1>LOGIN</h1>
            <label for="email">Email</label>
            <input type="text" id="email" name="email" required>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
            <a href="resetPassword" id="reset-link"> Reset Password </a>
            <button type="submit">Login</button>
            <p>Don't have an account? <a href="register">SIGNUP</a></p>
        </form>
    </div>
</body>
</html>
