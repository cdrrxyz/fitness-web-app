<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" href="/resources/css/register.css" />
</head>
<body>
  <jsp:include page="home.jsp" />
  <div class="register-form">
    <form action="register" method="post">
      <h1>Create Account</h1>
      <label for="firstname">First Name</label>
      <input type="text" id="firstname" name="firstname" required>
      <label for="lastname">Last Name</label>
      <input type="text" id="lastname" name="lastname" required>
      <label for="email">Email</label>
      <input type="text" id="email" name="email" required>
      <label for="password">Password</label>
      <input type="password" id="password" name="password" required>
      <button type="submit">Sign-up</button>
      <p>Already have an account? <a href="login">LOGIN</a></p>
    </form>
  </div>
</body>
</html>
