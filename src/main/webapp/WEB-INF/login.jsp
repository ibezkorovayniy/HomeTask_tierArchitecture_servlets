<%--
  Created by IntelliJ IDEA.
  User: illia
  Date: 12/14/17
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/servlet/login" method="post">

    <div class="field">
        <label>Enter your login:</label>
        <div class="input"><input type="text" name="email"/></div>
    </div>

    <div class="field">
        <a href="#" id="forgot">Forgot your password?</a>
        <label>Enter your password:</label>
        <div class="input"><input type="password" name="password"/></div>
    </div>

    <div class="submit">
        <button type="submit">Enter</button>
    </div>

</form>
</body>
</html>
