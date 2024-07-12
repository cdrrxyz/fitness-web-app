<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email Validation</title>
    <link rel="stylesheet" href="/resources/css/email.css" />
</head>
<body>
    <jsp:include page="home.jsp" />
    <div class="email-form">
        <form action="emailValidation" method="post">
            <h1>Check Your Email</h1>
            <p>We've sent a 4-digit verification code to your email.</p>
            <div class="verification-code">
                <input type="text" id="digit1" name="digit1" maxlength="1" required>
                <input type="text" id="digit2" name="digit2" maxlength="1" required>
                <input type="text" id="digit3" name="digit3" maxlength="1" required>
                <input type="text" id="digit4" name="digit4" maxlength="1" required>
            </div>
            <button type="submit">Verify Email</button>
            <p>Didn't receive the code? <a href="emailValidation.jsp">Resend code</a></p>
        </form>
    </div>
</body>
</html>
